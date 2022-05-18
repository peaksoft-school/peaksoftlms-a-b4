package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "test_results")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TestStudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String answer;
    private Boolean isTrue;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "student_entity_id")
    private StudentEntity studentEntity;

}
