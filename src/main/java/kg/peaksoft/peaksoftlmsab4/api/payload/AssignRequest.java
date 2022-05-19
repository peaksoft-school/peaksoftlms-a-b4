package kg.peaksoft.peaksoftlmsab4.api.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignRequest {

    private Long courseId;
    private Long teacherId;
}
