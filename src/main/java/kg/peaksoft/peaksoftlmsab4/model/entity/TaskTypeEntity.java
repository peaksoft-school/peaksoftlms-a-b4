package kg.peaksoft.peaksoftlmsab4.model.entity;

import kg.peaksoft.peaksoftlmsab4.model.enums.TaskType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "task_types")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TaskTypeEntity {

    @Id
    @SequenceGenerator(
            name = "taskType_sequence",
            sequenceName = "taskTypes_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "taskTypes_sequence"
    )
    Long id;
    private String value;
    @Enumerated(EnumType.STRING)
    private TaskType taskType;

}
