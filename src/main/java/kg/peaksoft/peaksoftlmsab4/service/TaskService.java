package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.TaskRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TaskResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.TaskEntity;

import java.util.List;

public interface TaskService {

    TaskResponse saveTask(Long id, TaskRequest taskRequest);

    TaskResponse getTaskById(Long id);

    List<TaskResponse> getAllTask();

    TaskResponse updateTask(Long id, TaskRequest taskRequest);

    TaskResponse deleteTask(Long id);
}
