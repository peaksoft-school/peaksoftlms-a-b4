package kg.peaksoft.peaksoftlmsab4.api.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class QuestionRequestForTest {
    @NotBlank
    private String question;
    private List<OptionRequestForTest> options;
}
