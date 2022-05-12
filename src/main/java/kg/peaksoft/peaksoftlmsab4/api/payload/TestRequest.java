package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@Setter
public class TestRequest {
    @JsonProperty("test_name")
    private String testName;
    private List<QuestionRequest> questions;
}
