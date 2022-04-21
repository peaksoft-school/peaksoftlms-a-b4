package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @OneToOne(cascade = CascadeType.ALL)
    private LinkEntity linkEntity;

    @OneToOne(cascade = CascadeType.ALL)
    private VideoEntity videoEntity;

    @OneToOne(cascade = CascadeType.ALL)
    private PresentationEntity presentationEntity;

    @ManyToOne
    private CourseEntity courseEntity;

//    @OneToOne
//    private Test test;
//
//    @OneToOne
//    private Task task;
}
