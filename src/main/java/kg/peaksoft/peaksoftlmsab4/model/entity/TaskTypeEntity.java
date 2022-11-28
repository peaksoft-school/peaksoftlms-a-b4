package kg.peaksoft.peaksoftlmsab4.model.entity;

import kg.peaksoft.peaksoftlmsab4.model.enums.TaskType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_types")
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
    private String name;
    @Enumerated(EnumType.STRING)
    private TaskType taskType;

}
