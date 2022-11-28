package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answers")
public class StudentsAnswers {

    @Id
    @SequenceGenerator(name = "answers_gen", sequenceName = "answers_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answers_gen")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private OptionEntity optionEntity;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private QuestionEntity questionEntity;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private TestEntity testEntity;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private StudentEntity studentEntity;

}
