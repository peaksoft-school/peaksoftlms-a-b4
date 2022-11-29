package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REFRESH;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tests")
public class TestEntity {

    @Id
    @SequenceGenerator(name = "tests_gen", sequenceName = "tests_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tests_gen")
    private Long id;

    private String testName;

    @OneToOne(cascade = {DETACH, MERGE, REFRESH}, fetch = FetchType.LAZY)
    private LessonEntity lessonEntity;

    @OneToMany(cascade = CascadeType.ALL)
    private List<QuestionEntity> questions;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TestStudentEntity> testStudentEntities;

}
