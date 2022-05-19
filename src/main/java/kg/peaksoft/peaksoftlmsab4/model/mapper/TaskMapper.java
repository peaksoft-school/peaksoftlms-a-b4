package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.TaskRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TaskResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.TaskEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TaskMapper {

    public TaskEntity mapToEntity(TaskRequest taskRequest) {
        if (taskRequest == null) {
            return null;
        }
        return TaskEntity.builder()
                .taskName(taskRequest.getTaskName())
                .taskTypes(taskRequest.getTaskTypeEntity())
                .build();

    }
    public TaskEntity update(TaskEntity taskEntity, TaskRequest taskRequest) {
        taskEntity.setTaskName(taskRequest.getTaskName());
        taskEntity.setTaskTypes(taskRequest.getTaskTypeEntity());
        return taskEntity;
    }

    public List<TaskResponse> mapToResponse(List<TaskEntity> tasks) {
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (TaskEntity taskEntity : tasks) {
            taskResponses.add(mapToResponse(taskEntity));
        }
        return taskResponses;
    }

    public TaskResponse mapToResponse(TaskEntity task) {
        return TaskResponse.builder()
                .id(task.getId())
                .taskName(task.getTaskName())
                .taskTypeEntity(task.getTaskTypes())
                .build();
    }
}

