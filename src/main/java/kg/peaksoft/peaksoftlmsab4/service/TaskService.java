package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.TaskRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.TaskResponse;

import java.util.List;

public interface TaskService {

    TaskResponse saveTask(Long id, TaskRequest taskRequest);

    TaskResponse getTaskById(Long id);

    List<TaskResponse> getAllTask();

    TaskResponse updateTask(Long id, TaskRequest taskRequest);

    TaskResponse deleteTask(Long id);
}
