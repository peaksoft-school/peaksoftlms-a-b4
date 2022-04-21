package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CourseEntity {
    @Id
    @SequenceGenerator(
            name = "courses_sequence",
            sequenceName = "courses_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "courses_sequence"
    )
    private Long id;
    private String courseName;
    private LocalDate dateOfStart;
    private String description;
    private String image;

    @ManyToMany(mappedBy = "courses", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    private List<GroupEntity> groups = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "courses_instructors", joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    private List<InstructorEntity> instructors;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(name = "courses_students",joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<StudentEntity>students;

    @OneToMany(mappedBy = "courseEntity",cascade = CascadeType.ALL)
    private List<LessonEntity> lessons;

    @PreRemove
    private void removeGroupFromCourses() {
        for (GroupEntity group : groups) {
            group.getCourses().remove(this);
        }
    }

    public void setGroup(GroupEntity group) {
        if (groups == null) {
            groups = new ArrayList<>();
        }
        groups.add(group);

    }

    public void setInstructor(InstructorEntity instructor) {
        if (instructors == null) {
            instructors = new ArrayList<>();
        }
        instructors.add(instructor);
    }

    public void setStudent(StudentEntity student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }



}
