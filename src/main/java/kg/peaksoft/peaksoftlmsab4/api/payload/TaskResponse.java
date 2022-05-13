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
    @JsonProperty("file_link")
    private String fileLink;
    private String link;
    @JsonProperty("image_link")
    private String imageLink;
    private String code;
}
