package kg.peaksoft.peaksoftlmsab4.model.entity;

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
    @OneToOne(cascade = {DETACH, MERGE, REFRESH})
    private LessonEntity lessonEntity;
}
