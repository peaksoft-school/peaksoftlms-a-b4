package kg.peaksoft.peaksoftlmsab4.controller.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestRequest {

    private String testName;
    private List<QuestionRequest> questions;
    private Long lessonId;

}
