package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerEntity {
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
    private String student;
    private Long questionId;
    private int result;
}
