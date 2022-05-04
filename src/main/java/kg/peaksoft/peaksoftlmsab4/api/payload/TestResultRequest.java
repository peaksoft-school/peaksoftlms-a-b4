package kg.peaksoft.peaksoftlmsab4.api.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TestResultRequest {
    @NotBlank
    private Long optionId;
    @NotBlank
    private String answer;
    @NotBlank
    private Boolean isTrue;


}
