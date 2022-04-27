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

    public TaskEntity mapToEntity(TaskRequest taskRequest, Long id) {
        if (taskRequest == null) {
            return null;
        }

        return TaskEntity.builder()
                .id(id)
                .taskName(taskRequest.getTaskName())
                .text(taskRequest.getText())
                .fileFormat(taskRequest.getFileFormat())
                .link(taskRequest.getLink())
                .code(taskRequest.getCode())
                .image(taskRequest.getImage())
                .build();
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
                .text(task.getText())
                .fileFormat(task.getFileFormat())
                .link(task.getLink())
                .code(task.getCode())
                .image(task.getImage())
                .build();
    }
}

