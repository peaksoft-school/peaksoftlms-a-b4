package kg.peaksoft.peaksoftlmsab4.controller.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LessonResponse {

    private Long id;
    private String lessonName;

}
