package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.*;

import javax.persistence.*;

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
    private String presentationName;
    private String description;
    private String link;

    @OneToOne
    private LessonEntity lessonEntity;
}
