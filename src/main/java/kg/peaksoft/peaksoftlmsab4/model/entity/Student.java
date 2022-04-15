package kg.peaksoft.peaksoftlmsab4.model.entity;

import kg.peaksoft.peaksoftlmsab4.model.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "mobile_phone")
    String mobilePhone;
    String email;
    @Column(name = "study_format")
    @Enumerated(EnumType.STRING)
    StudyFormat studyFormat;
}
