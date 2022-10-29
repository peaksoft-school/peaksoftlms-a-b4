package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "answers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentsAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
