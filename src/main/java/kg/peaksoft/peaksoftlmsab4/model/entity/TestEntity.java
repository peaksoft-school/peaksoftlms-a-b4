package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "tests")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String testName;

    @OneToOne(cascade = {DETACH, MERGE, REFRESH},fetch = FetchType.LAZY)
    private LessonEntity lessonEntity;

    @OneToMany( cascade = CascadeType.ALL)
    private List<QuestionEntity> questions = new ArrayList<>();

    @OneToMany( cascade = CascadeType.ALL)
    private List<TestStudentEntity> testStudentEntities;
}
