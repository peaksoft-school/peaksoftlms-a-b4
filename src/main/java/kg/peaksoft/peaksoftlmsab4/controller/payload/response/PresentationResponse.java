package kg.peaksoft.peaksoftlmsab4.controller.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PresentationResponse {
    Long id;
    private String presentationName;
    private String description;
    private String presentationLink;
    private Long lessonId;
}
