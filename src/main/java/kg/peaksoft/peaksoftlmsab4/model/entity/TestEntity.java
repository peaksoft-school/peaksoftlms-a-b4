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
    private Long id;
    private String testName;

    @OneToOne(fetch = FetchType.LAZY)
    private LessonEntity lessonEntity;

    @OneToMany( cascade = CascadeType.ALL)
    private List<QuestionEntity> questions = new ArrayList<>();


}
