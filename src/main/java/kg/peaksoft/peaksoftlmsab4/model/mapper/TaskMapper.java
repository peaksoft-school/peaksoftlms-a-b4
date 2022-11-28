package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.TaskRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.TaskResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.request.TaskTypeRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.TaskEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TaskTypeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TaskMapper {

    private final TaskTypeMapper taskTypeMapper;

    public TaskEntity mapToEntity(TaskRequest taskRequest) {
        if (taskRequest == null) {
            return null;
        }
        List<TaskTypeEntity> taskTypeEntity = new ArrayList<>();
        for (TaskTypeRequest t : taskRequest.getTaskTypeRequests()) {
            taskTypeEntity.add(taskTypeMapper.mapToEntity(t));
        }
        return TaskEntity.builder()
                .taskName(taskRequest.getTaskName())
                .taskTypes(taskTypeEntity)
                .build();
    }

    public TaskEntity update(TaskEntity taskEntity, TaskRequest taskRequest) {
        List<TaskTypeEntity> taskTypeEntity = new ArrayList<>();
        for (TaskTypeRequest t : taskRequest.getTaskTypeRequests()) {
            taskTypeEntity.add(taskTypeMapper.mapToEntity(t));
        }
        taskEntity.setTaskName(taskRequest.getTaskName());
        taskEntity.setTaskTypes(taskTypeEntity);
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
        if (task == null) {
            return null;
        }
        return TaskResponse.builder()
                .id(task.getId())
                .taskName(task.getTaskName())
                .taskTypeResponses(taskTypeMapper.mapToResponse(task.getTaskTypes()))
                .lessonId(task.getLesson().getId())
                .build();
    }

}

