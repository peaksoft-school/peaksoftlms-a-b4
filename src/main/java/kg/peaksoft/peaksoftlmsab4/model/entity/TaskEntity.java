package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TaskEntity {

    @Id
    @SequenceGenerator(
            name = "tasks_sequence",
            sequenceName = "tasks_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tasks_sequence"
    )
    Long id;
    @Column(name = "task_name")
    private String taskName;
    private String text;
    @Column(name = "file_link")
    private String fileLink;
    private String link;
    @Column(name = "image_link")
    private String imageLink;
    private String code;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "lesson_id")
    private LessonEntity lesson;
}
