package kg.peaksoft.peaksoftlmsab4.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Builder
@Entity
@Table(name = "links")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LinkEntity {
    @Id
    @SequenceGenerator(
            name = "links_sequence",
            sequenceName = "links_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "links_sequence"
    )
    Long id;

    private String text;
    private String link;

    @OneToOne(cascade = {MERGE, DETACH, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private LessonEntity lessonEntity;
}
