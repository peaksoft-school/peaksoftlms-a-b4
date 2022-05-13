package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Builder
@Entity
@Table(name = "videos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoEntity {
    @Id
    @SequenceGenerator(
            name = "videos_sequence",
            sequenceName = "videos_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "videos_sequence"
    )
    private Long id;
    @Column(name = "video_name")
    private String videoName;
    private String description;
    @Column(name = "video_link")
    private String videoLink;
    @OneToOne(cascade = {DETACH, MERGE, REFRESH})
    private LessonEntity lessonEntity;
}
