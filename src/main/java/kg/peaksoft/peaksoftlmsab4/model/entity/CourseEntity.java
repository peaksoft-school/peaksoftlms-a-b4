package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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
//    @ManyToMany
//    private List<GroupEntity> groups;
}
