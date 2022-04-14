CREATE TYPE authority AS ENUM ('ADMIN', 'INSTRUCTOR');
CREATE TYPE study_format AS ENUM ('ONLINE', 'OFFLINE');

CREATE TABLE IF NOT EXISTS auth_info (

    id serial PRIMARY KEY NOT NULL,
    email varchar(50) not null unique,
    password varchar(250) not null,
    authority_type authority,
    is_account_non_expired boolean,
    is_account_non_locked boolean,
    is_credentials_non_expired boolean,
    is_enabled boolean
);

CREATE TABLE IF NOT EXISTS admins (

    id serial PRIMARY KEY NOT NULL,
    first_name varchar(250),
    last_name varchar(250),
    auth_info_id bigint references auth_info(id)
    );
CREATE TABLE IF NOT EXISTS instructors (

    id serial PRIMARY KEY NOT NULL,
    first_name varchar(250),
    last_name varchar(250),
    mobile_phone varchar (250),
    specialization varchar (250),
    auth_info_id bigint references auth_info(id)
);
CREATE TABLE IF NOT EXISTS students (

    id serial PRIMARY KEY NOT NULL,
    first_name varchar(250),
    last_name varchar(250),
    mobile_phone varchar (250),
    email varchar (250),
    study_format_type study_format
);

insert into auth_info(email, password, authority_type, is_account_non_expired,
            is_account_non_locked, is_credentials_non_expired, is_enabled) values
            ('kairabek@gmail.com', '$2a$04$bgaWpBkJrajY/yO9sbjPe.dAZkMCGp.XZEJiwhFxOyLS6ENMe5Y7m',
            'ADMIN',true,true,true,true),
            ('chyngyz@gmail.com', '$2a$04$e4H6sH5Hk6.rzt.BE0LZTuiUN12iHIA591QWMJ8TMFRR9./OKsP4e',
            'INSTRUCTOR',true,true,true,true);

insert into admins(first_name, last_name, auth_info_id) values
            ('Kairatbek', 'Shabotoev', (
            select id from auth_info where email = 'kairabek@gmail.com'
            ));

insert into instructors(first_name, last_name, mobile_phone, specialization, auth_info_id) values
            ('Chyngyz', 'Kamarov', '0779252525', 'java-instructor',
            (select id from auth_info where email = 'chyngyz@gmail.com'));

insert into students(first_name, last_name, mobile_phone, email,study_format_type) values
            ('Meerim', 'Asanova', '0779222222', 'meerim@gmail.com','OFFLINE');