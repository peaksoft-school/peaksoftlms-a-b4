package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.PERSIST;

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
    private String videoName;
    private String description;
    private String link;
    @OneToOne(cascade = {DETACH, MERGE, REFRESH, PERSIST},fetch = FetchType.LAZY)
    private LessonEntity lessonEntity;
}
