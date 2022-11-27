package kg.peaksoft.peaksoftlmsab4.controller.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NotBlank
public class VideoRequest {

    private String videoName;
    private String description;
    private String videoLink;

}
