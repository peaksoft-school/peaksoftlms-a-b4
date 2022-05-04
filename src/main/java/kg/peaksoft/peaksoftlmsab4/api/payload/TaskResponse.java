package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TaskResponse {

    private Long id;
    @JsonProperty("task_name")
    private String taskName;
    private String text;
    @JsonProperty("file_format")
    private String fileFormat;
    private String link;
    @JsonProperty("image_link")
    private String image;
    private String code;
}
