package kg.peaksoft.peaksoftlmsab4.controller.payload.response;

import kg.peaksoft.peaksoftlmsab4.model.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionResponse {

    private Long id;
    private String question;
    private QuestionType questionType;
    private List<OptionResponse> options;

}
