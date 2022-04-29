package kg.peaksoft.peaksoftlmsab4.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GroupEntity {
    @Id
    @SequenceGenerator(
            name = "groups_sequence",
            sequenceName = "groups_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "groups_sequence"
    )
    private Long id;
    private String groupName;
    private LocalDate dateOfStart;
    private String description;
    private String image;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinTable(name = "course_group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<CourseEntity> courses = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<StudentEntity> students;

    @JsonIgnore
    public void setCourse(CourseEntity course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        course.setGroup(this);
    }

    public void setStudent(StudentEntity student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
        student.setGroup(this);
    }
}
