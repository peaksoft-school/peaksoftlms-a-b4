package kg.peaksoft.peaksoftlmsab4.entity;

import kg.peaksoft.peaksoftlmsab4.enumPackage.StudyFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name = "study_format")
    @Enumerated(EnumType.STRING)
    StudyFormat studyFormat;
    @OneToOne
    AuthInfo authInfo;
}
