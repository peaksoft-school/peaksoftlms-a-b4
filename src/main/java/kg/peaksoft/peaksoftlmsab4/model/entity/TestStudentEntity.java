package kg.peaksoft.peaksoftlmsab4.model.entity;

import kg.peaksoft.peaksoftlmsab4.model.enums.TestResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "test_results")
public class TestStudentEntity {

    @Id
    @SequenceGenerator(name = "test_results_gen", sequenceName = "test_results_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_results_gen")
    private Long id;

    private int result;

    @Enumerated(EnumType.STRING)
    private TestResult testResult;

    @CreatedDate
    private LocalDate localDate;

    @OneToOne(cascade = CascadeType.ALL)
    private StudentEntity studentEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    private TestEntity testEntity;

}
