package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponse {

    @JsonProperty("task_name")
    String taskName;
    String text;
    @JsonProperty("file_format")
    String fileFormat;
    String link;
    String image;
    String code;
}
