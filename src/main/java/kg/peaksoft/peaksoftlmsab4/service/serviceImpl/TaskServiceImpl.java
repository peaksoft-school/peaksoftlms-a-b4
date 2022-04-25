package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.TaskRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TaskResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.TaskEntity;
import kg.peaksoft.peaksoftlmsab4.service.TaskService;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    @Override
    public TaskResponse saveTask(Long id, TaskRequest taskRequest) {
        return null;
    }

    @Override
    public TaskEntity getTaskById(Long id) {
        return null;
    }

    @Override
    public List<TaskResponse> getAllTask() {
        return null;
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        return null;
    }

    @Override
    public void deleteTask(Long id) {

    }
}
