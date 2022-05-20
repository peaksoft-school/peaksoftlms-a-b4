package kg.peaksoft.peaksoftlmsab4.api.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class TestStudentRequest {

    private Long testId;
    private List<QuestionRequestForTest> questions;

}