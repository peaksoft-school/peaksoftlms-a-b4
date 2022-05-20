package kg.peaksoft.peaksoftlmsab4.api.payload;


import kg.peaksoft.peaksoftlmsab4.model.entity.TaskTypeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NotBlank
public class TaskRequest {

    private String taskName;
    private List<TaskTypeEntity> taskTypeEntity;
}
