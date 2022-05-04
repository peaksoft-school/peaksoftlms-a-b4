package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NotBlank
public class TaskRequest {

    @JsonProperty("task_name")
    private String taskName;
    private String text;
    @JsonProperty("file_format")
    private MultipartFile fileFormat;
    private String link;
    private MultipartFile image;
    private String code;
}
