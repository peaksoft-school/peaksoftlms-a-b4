package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class VideoResponse {
    Long id;
    @JsonProperty("video_name")
    private String videoName;
    private String description;
    @JsonProperty("video_link")
    private String videoLink;
}
