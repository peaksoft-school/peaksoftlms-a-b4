package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Builder
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

    public CourseEntity(String courseName) {
        this.courseName =courseName;
    }
//    @ManyToMany
//    private List<GroupEntity> groups;
}
