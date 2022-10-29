package kg.peaksoft.peaksoftlmsab4.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "presentations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PresentationEntity {
    @Id
    @SequenceGenerator(
            name = "presentations_sequence",
            sequenceName = "presentations_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "presentations_sequence"
    )
    private Long id;
    @Column(name = "presentation_name")
    private String presentationName;
    private String description;
    @Column(name = "presentation_link")
    private String presentationLink;

    @OneToOne(cascade = {MERGE, DETACH, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private LessonEntity lessonEntity;
}
