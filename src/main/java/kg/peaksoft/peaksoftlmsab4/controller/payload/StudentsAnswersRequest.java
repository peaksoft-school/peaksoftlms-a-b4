package kg.peaksoft.peaksoftlmsab4.controller.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentsAnswersRequest {
    private Long testId;
    private List<QuestionRequestForTest> questions;
}
