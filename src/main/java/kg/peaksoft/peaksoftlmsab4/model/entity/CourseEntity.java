package kg.peaksoft.peaksoftlmsab4.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private LocalDate dateOfStart;
    private String description;
    private String image;

    @ManyToMany(mappedBy = "courses", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    private List<GroupEntity> groups = new ArrayList<>();

    @PreRemove
    private void removeCourseFromCourses() {
        for (GroupEntity group : groups) {
            group.getCourses().remove(this);
        }
    }

    @JsonIgnore
    public void setGroup(GroupEntity group) {
        if (group == null) {
            groups = new ArrayList<>();
        }
        groups.add(group);

    }
}
