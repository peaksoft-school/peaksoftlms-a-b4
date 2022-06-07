package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.TaskTypeRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TaskTypeResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.TaskTypeEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskTypeMapper {
    public TaskTypeEntity mapToEntity(TaskTypeRequest taskTypeRequest) {
        if (taskTypeRequest == null) {
            return null;
        }
        return TaskTypeEntity.builder()
                .value(taskTypeRequest.getValue())
                .name(taskTypeRequest.getName())
                .taskType(taskTypeRequest.getTaskType())
                .build();

    }

    public TaskTypeResponse mapToResponse(TaskTypeEntity taskTypeEntity) {
        return TaskTypeResponse.builder()
                .id(taskTypeEntity.getId())
                .taskType(taskTypeEntity.getTaskType())
                .name(taskTypeEntity.getName())
                .value(taskTypeEntity.getValue())
                .build();
    }

    public List<TaskTypeResponse> mapToResponse(List<TaskTypeEntity> tasks) {
        List<TaskTypeResponse> taskTypeResponses = new ArrayList<>();
        for (TaskTypeEntity taskTypeEntity : tasks) {
            taskTypeResponses.add(mapToResponse(taskTypeEntity));
        }
        return taskTypeResponses;
    }
}
