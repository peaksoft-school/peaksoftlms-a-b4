package kg.peaksoft.peaksoftlmsab4.model.entity;

import kg.peaksoft.peaksoftlmsab4.model.enums.StudyFormat;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Builder
@ToString
@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {
    @Id
    @SequenceGenerator(
            name = "students_sequence",
            sequenceName = "students_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "students_sequence"
    )
    Long id;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "phone_number")
    String phoneNumber;
    @Column(name = "study_format")
    @Enumerated(EnumType.STRING)
    StudyFormat studyFormat;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    AuthInfo authInfo;

    @ManyToOne(cascade = {DETACH, MERGE, REFRESH})
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "students")
    private List<CourseEntity> courses;

    @OneToOne(cascade = ALL)
    private TestStudentEntity testStudentEntity;



    public void setCourse(CourseEntity course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        course.setStudent(this);
    }

    @PreRemove
    private void removeStudentFromCourses() {
        for (CourseEntity course : courses) {
            course.getStudents().remove(this);
        }
    }

}


