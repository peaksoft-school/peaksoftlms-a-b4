package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.TaskRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.TaskResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TaskEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.TaskMapper;
import kg.peaksoft.peaksoftlmsab4.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TaskRepository;
import kg.peaksoft.peaksoftlmsab4.service.TaskService;
import kg.peaksoft.peaksoftlmsab4.validation.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.validation.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;

    @Override
    public TaskResponse saveTask(Long id, TaskRequest request) {
        LessonEntity lessonEntity = lessonRepository.findById(id).orElseThrow(() -> {
            log.error("Lesson with id = {} does not exists", id);
            throw new NotFoundException(String.format("Lesson with id = %s does not exists", id));
        });
        if (lessonEntity.getTaskEntity() == null) {
            TaskEntity task = taskMapper.mapToEntity(request);
            task.setLesson(lessonEntity);
            log.info(" Task with name : {} has successfully saved to database", task.getTaskName());
            return taskMapper.mapToResponse(taskRepository.save(task));
        } else {
            throw new BadRequestException("In this lesson task already exists");
        }
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        log.info("Found task with id :{}", id);
        return taskMapper.mapToResponse(taskRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Not found id=%s", id))));

    }

    @Override
    public List<TaskResponse> getAllTask() {
        log.info("Found {} tasks ", taskRepository.findAll().size());
        return taskMapper.mapToResponse(taskRepository.findAll());
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest request) {
        TaskEntity task = taskRepository.findById(id).orElseThrow(() -> {
            log.error("Task with id ={} does not exists", id);
            throw new NotFoundException(String.format("task with id = %s does not exists", id));
        });
        taskMapper.update(task, request);
        taskRepository.save(task);
        return taskMapper.mapToResponse(task);
    }

    @Override
    public TaskResponse deleteTask(Long id) {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(String.format("task with id = %s does not exists", id));
        });
        taskRepository.delete(taskEntity);
        return taskMapper.mapToResponse(taskEntity);
    }

}
