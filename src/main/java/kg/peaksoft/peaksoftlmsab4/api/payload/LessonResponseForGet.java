package kg.peaksoft.peaksoftlmsab4.api.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LessonResponseForGet {
    private Long id;
    private String lessonName;
    private LinkResponse linkResponse;
    private VideoResponse videoResponse;
    private PresentationResponse presentationResponse;
    private TaskResponse taskResponse;
    private TestResponse testResponse;
}
