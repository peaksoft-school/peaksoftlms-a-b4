package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answers")
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
