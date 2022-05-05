package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NotBlank
public class VideoRequest {
    @JsonProperty("video_name")
    private String videoName;
    private String description;
    @JsonProperty("video_link")
    private String videoLink;
}
