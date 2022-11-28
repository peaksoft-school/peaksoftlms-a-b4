package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REFRESH;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessons")
public class LessonEntity {

    @Id
    @SequenceGenerator(name = "lessons_gen", sequenceName = "lessons_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lessons_gen")
    Long id;

    @Column(name = "lesson_name")
    private String lessonName;

    @OneToOne(cascade = ALL, fetch = FetchType.EAGER, mappedBy = "lessonEntity")
    private LinkEntity linkEntity;

    @OneToOne(cascade = ALL, fetch = FetchType.EAGER, mappedBy = "lessonEntity")
    private VideoEntity videoEntity;

    @OneToOne(cascade = ALL, fetch = FetchType.EAGER, mappedBy = "lessonEntity")
    private PresentationEntity presentationEntity;

    @OneToOne(cascade = ALL, mappedBy = "lessonEntity")
    private TestEntity testEntity;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH})
    private CourseEntity courseEntity;

    @OneToOne(cascade = ALL, fetch = FetchType.EAGER, mappedBy = "lesson")
    private TaskEntity taskEntity;

}
