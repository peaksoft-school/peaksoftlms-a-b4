package kg.peaksoft.peaksoftlmsab4.controller.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NotBlank
public class LessonRequest {
    private String lessonName;
}
