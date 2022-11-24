package kg.peaksoft.peaksoftlmsab4.controller.payload;

import kg.peaksoft.peaksoftlmsab4.model.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class QuestionRequest {


    private String question;
    private QuestionType questionType;

    private List<OptionRequest> options;


}
