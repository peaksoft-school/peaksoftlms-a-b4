package kg.peaksoft.peaksoftlmsab4.api.payload;

import kg.peaksoft.peaksoftlmsab4.model.enums.TaskType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TaskTypeResponse {

    private Long id;
    private String value;
    private String name;
    private TaskType taskType;
}
