package kg.peaksoft.peaksoftlmsab4.api.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestResultResponse {

    private Long id;
    private Long correctAnswer;
    private Long wrongAnswer;
    private Long point;
}
