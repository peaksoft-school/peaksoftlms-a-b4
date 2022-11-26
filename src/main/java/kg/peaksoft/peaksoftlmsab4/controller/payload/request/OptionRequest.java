package kg.peaksoft.peaksoftlmsab4.controller.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OptionRequest {
    @NotBlank
    private String option;
    @NotBlank
    private Boolean isTrue;

}
