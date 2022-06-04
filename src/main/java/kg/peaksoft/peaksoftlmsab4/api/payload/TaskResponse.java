package kg.peaksoft.peaksoftlmsab4.api.payload;

import kg.peaksoft.peaksoftlmsab4.model.entity.TaskTypeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TaskResponse {

    private Long id;
    private String taskName;
    private List<TaskTypeResponse> taskTypeResponses;
    private Long lessonId;
}
