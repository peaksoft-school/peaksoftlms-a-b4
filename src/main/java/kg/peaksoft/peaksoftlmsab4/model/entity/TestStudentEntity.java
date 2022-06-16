package kg.peaksoft.peaksoftlmsab4.model.entity;

import kg.peaksoft.peaksoftlmsab4.model.enums.TestResult;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
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
