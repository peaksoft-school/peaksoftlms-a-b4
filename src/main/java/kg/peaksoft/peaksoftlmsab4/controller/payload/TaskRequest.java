package kg.peaksoft.peaksoftlmsab4.controller.payload;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NotBlank
public class TaskRequest {

    private String taskName;
    private List<TaskTypeRequest> taskTypeRequests;
}
