package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.TaskRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TaskResponse;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.*;
import kg.peaksoft.peaksoftlmsab4.model.mapper.TaskMapper;
import kg.peaksoft.peaksoftlmsab4.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TaskRepository;
import kg.peaksoft.peaksoftlmsab4.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;

    @Override
    public TaskResponse saveTask(Long id,TaskRequest taskRequest) {
        LessonEntity lessonEntity = lessonRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Lesson with id = {} does not exists", id);
                    throw new NotFoundException(
                            String.format("Lesson with id = %s does not exists", id)
                    );
                });
        TaskEntity task = taskMapper.mapToEntity(taskRequest, null);
        task.setLesson(lessonEntity);
        log.info(" Task with name : {} has successfully saved to database", task.getTaskName());
        return taskMapper.mapToResponse(taskRepository.save(task));

    }

    @Override
    public TaskResponse getTaskById(Long id) {
        TaskEntity task=taskRepository.findById(id).
               orElseThrow(() -> {
                           log.error("Task with id = {} does not exists", id);
                           throw new NotFoundException(String.format("Task with id = %s id not found ", id));
                       });
        log.info("Found task with id = {} ",id);
        return taskMapper.mapToResponse(task);

    }

    @Override
    public List<TaskResponse> getAllTask() {
        log.info("Found {} tasks ", taskRepository.findAll().size());
        return taskMapper.mapToResponse(taskRepository.findAll());
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        TaskEntity task=taskRepository.findById(id)
                 .orElseThrow(()-> {
            log.error("Task with id ={} does not exists",id);
            throw new NotFoundException(
                    String.format("task with id = %s does not exists",id)
            );
        });
        taskMapper.mapToEntity(taskRequest,task.getId());
        log.info("Task with id = {} updated",id);
        return taskMapper.mapToResponse(taskRepository.save(taskMapper.mapToEntity(taskRequest,id)));
    }

    @Override
    public void deleteTask(Long id) {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(()-> {
                    log.error("Task with id = {} does not exists", id);
                    throw new NotFoundException(String.format("task with id = %s does not exists",id));
                });
        taskRepository.delete(taskEntity);
        log.info("Task with id = {} has successfully deleted", id);
    }
}
