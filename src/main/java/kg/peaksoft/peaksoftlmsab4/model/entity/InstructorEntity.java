package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorEntity {
    @Id
    @SequenceGenerator(
            name = "instructors_sequence",
            sequenceName = "instructors_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "instructors_sequence"
    )
    Long id;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "mobile_phone")
    String mobilePhone;
    String specialization;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    AuthInfo authInfo;

    @ManyToMany(cascade ={ CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE},fetch = FetchType.LAZY,mappedBy = "instructors")
    private List<CourseEntity> courses;

    public void setCourse(CourseEntity course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        course.setInstructor(this);
    }

    @PreRemove
    private void removeInstructorFromCourses() {
        for (CourseEntity course : courses) {
            course.getInstructors().remove(this);
        }
    }
}