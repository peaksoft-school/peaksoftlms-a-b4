package kg.peaksoft.peaksoftlmsab4.api.payload;

import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.QuestionEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerRequest {
    private Long testId;
    private List<QuestionRequestForTest> question;
}
