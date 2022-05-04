package kg.peaksoft.peaksoftlmsab4.api.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultResponse {

    private String studentName;
    private Long error;
    private Long correct;
    private Long point;
}
