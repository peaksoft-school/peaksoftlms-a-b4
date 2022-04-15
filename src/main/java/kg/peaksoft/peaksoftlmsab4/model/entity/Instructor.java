package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "instructors")
@Getter
@Setter
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "mobile_phone")
    String mobilePhone;
    String specialization;
    @OneToOne(cascade = CascadeType.ALL)
    AuthInfo authInfo;

}
