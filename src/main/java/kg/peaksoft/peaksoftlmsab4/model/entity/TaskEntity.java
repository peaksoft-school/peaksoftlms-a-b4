package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TaskEntity {

    @Id
    @SequenceGenerator(
            name = "tasks_sequence",
            sequenceName = "tasks_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tasks_sequence"
    )
    Long id;
    @Column(name = "task_name")
    private String taskName;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<TaskTypeEntity > taskTypes;

    @OneToOne (cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    private LessonEntity lesson;
}
