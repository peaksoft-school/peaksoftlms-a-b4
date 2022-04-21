package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "presentations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    Long id;
    private String presentationName;
    private String description;
    private String link;
}
