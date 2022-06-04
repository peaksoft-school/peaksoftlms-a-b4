package kg.peaksoft.peaksoftlmsab4.api.payload;

import kg.peaksoft.peaksoftlmsab4.model.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@Setter
public class QuestionRequest {

    @NotBlank
    private String question;
    private QuestionType questionType;

    private List<OptionRequest> options;


}
