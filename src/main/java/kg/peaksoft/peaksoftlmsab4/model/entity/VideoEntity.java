package kg.peaksoft.peaksoftlmsab4.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToOne(cascade = {MERGE, DETACH, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private LessonEntity lessonEntity;
}
