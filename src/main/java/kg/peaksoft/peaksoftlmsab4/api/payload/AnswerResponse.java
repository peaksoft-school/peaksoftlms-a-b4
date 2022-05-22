package kg.peaksoft.peaksoftlmsab4.api.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerResponse {
    private String student;
    private Long questionId;
    private int result;
}
