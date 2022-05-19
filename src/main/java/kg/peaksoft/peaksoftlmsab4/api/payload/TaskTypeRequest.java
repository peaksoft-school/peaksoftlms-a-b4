package kg.peaksoft.peaksoftlmsab4.api.payload;

import kg.peaksoft.peaksoftlmsab4.model.enums.TaskType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NotBlank
public class TaskTypeRequest {

    private String value;
    private TaskType taskType;
}
