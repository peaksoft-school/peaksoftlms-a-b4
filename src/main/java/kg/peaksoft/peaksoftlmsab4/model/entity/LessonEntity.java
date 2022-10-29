package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lessons")
public class LessonEntity {
    @Id
    @SequenceGenerator(
            name = "lessons_sequence",
            sequenceName = "lessons_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lessons_sequence"
    )
    Long id;
    @Column(name = "lesson_name")
    private String lessonName;

    @OneToOne(cascade = ALL, fetch = FetchType.EAGER, mappedBy = "lessonEntity")
    private LinkEntity linkEntity;

    @OneToOne(cascade = ALL, fetch = FetchType.EAGER, mappedBy = "lessonEntity")
    private VideoEntity videoEntity;

    @OneToOne(cascade = ALL, fetch = FetchType.EAGER, mappedBy = "lessonEntity")
    private PresentationEntity presentationEntity;

    @OneToOne(cascade = ALL,mappedBy = "lessonEntity")
    private TestEntity testEntity;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH})
    private CourseEntity courseEntity;

    @OneToOne(cascade = ALL, fetch = FetchType.EAGER, mappedBy = "lesson")
    private TaskEntity taskEntity;
}
