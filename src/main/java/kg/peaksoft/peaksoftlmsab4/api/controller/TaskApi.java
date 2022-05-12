package kg.peaksoft.peaksoftlmsab4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.api.payload.TaskRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TaskResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.TaskEntity;
import kg.peaksoft.peaksoftlmsab4.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/tasks")
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@Tag(name = "Task", description = "The Task CRUD operations")
public class TaskApi {

    private final TaskService taskService;

    @Operation(summary = "Creates new entity: Task",
            description = "This method saves new task. Only instructors can add new task to lesson")
    @PostMapping("/{lessonId}")
    public TaskResponse saveTask(@RequestBody TaskRequest taskRequest,@PathVariable Long lessonId){
        return taskService.saveTask(lessonId,taskRequest);
    }

    @Operation(summary = "Gets a single tasks by identifier",
            description = "For valid response try integer IDs with value >= 1 and...")
    @GetMapping("/{id}")
    public TaskEntity GetTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @Operation(summary = "Gets all existed tasks", description = "Returns all tasks in a list ")
    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTask();
    }

    @Operation(summary = "Updates the task ", description = "Updates the details of an endpoint with ID ")
    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(id, taskRequest);
    }

    @Operation(summary = "Deletes the single task", description = "Delete task with ID")
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
