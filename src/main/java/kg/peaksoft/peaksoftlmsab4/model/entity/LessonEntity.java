package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Builder
@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private String lessonName;

    @OneToOne(cascade = ALL,fetch = FetchType.EAGER)
    private LinkEntity linkEntity;

    @OneToOne(cascade = ALL,fetch = FetchType.EAGER)
    private VideoEntity videoEntity;

    @OneToOne(cascade = ALL,fetch = FetchType.EAGER)
    private PresentationEntity presentationEntity;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH},fetch = FetchType.LAZY )
    private CourseEntity courseEntity;
}
