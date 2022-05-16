package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NotBlank
public class TaskRequest {

    private String taskName;
    private String text;
    private String fileLink;
    private String link;
    private String imageLink;
    private String code;
}
