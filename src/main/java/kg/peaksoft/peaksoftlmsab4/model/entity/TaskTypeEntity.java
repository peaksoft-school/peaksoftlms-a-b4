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
            name = "task_types_sequence",
            sequenceName = "task_types_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_types_sequence"
    )
    Long id;
    private String value;
    private TaskType taskType;

}
