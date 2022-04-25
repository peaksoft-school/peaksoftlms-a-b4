package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TestResultRequest {
    @NotBlank
    @JsonProperty("correct_answer")
    private Long correctAnswer;
    @NotBlank
    @JsonProperty("wrong_answer")
    private Long wrongAnswer;
    @NotBlank
    private Long point;

}
