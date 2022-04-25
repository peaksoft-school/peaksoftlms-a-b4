package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NotBlank
public class TaskRequest {

    @JsonProperty("task_name")
    String taskName;
    String text;
    @JsonProperty("file_format")
    String fileFormat;
    String link;
    String image;
    String code;
}
