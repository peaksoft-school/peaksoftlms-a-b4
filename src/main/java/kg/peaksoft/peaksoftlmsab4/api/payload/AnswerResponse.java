package kg.peaksoft.peaksoftlmsab4.api.payload;

import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.TestResult;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AnswerResponse {
    private Long id;
    private int grade;
    private TestResult status;
    private LocalDate date;
    private String studentName;
}
