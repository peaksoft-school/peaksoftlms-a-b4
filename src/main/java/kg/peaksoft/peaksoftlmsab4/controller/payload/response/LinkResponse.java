package kg.peaksoft.peaksoftlmsab4.controller.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LinkResponse {
    Long id;
    private String text;
    private String link;
    private Long lessonId;
}
