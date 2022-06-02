package kg.peaksoft.peaksoftlmsab4.api.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VideoResponse {
    Long id;
    private String videoName;
    private String description;
    private String videoLink;
    private Long lessonId;
}
