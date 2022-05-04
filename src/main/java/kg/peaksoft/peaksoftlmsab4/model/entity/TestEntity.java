package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tests")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String testName;
    private int countTest;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private LessonEntity lessonEntity;

    @OneToMany(mappedBy = "testEntity", cascade = CascadeType.ALL)
    private List<QuestionEntity> questions = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "test_result_entity_id")
    private TestResultEntity testResultEntity;

}
