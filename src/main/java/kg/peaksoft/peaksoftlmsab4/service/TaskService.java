package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.TaskRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.TaskResponse;

import java.util.List;

public interface TaskService {

    TaskResponse saveTask(Long id, TaskRequest request);

    TaskResponse getTaskById(Long id);

    List<TaskResponse> getAllTask();

    TaskResponse updateTask(Long id, TaskRequest request);

    TaskResponse deleteTask(Long id);

}
