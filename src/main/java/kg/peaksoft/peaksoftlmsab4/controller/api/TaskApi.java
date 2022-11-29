package kg.peaksoft.peaksoftlmsab4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.controller.payload.request.TaskRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.TaskResponse;
import kg.peaksoft.peaksoftlmsab4.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tasks")
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Task API", description = "Task endpoints")
public class TaskApi {

    private final TaskService taskService;

    @Operation(summary = "Creates new entity: Task", description = "This method saves new task. Only instructors can add new task to lesson")
    @PostMapping("{lessonId}")
    public TaskResponse saveTask(@RequestBody TaskRequest request, @PathVariable Long lessonId) {
        return taskService.saveTask(lessonId, request);
    }

    @Operation(summary = "Gets a single tasks by identifier", description = "For valid response try integer IDs with value >= 1 and...")
    @GetMapping("{id}")
    public TaskResponse GetTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @Operation(summary = "Gets all existed tasks", description = "Returns all tasks in a list ")
    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTask();
    }

    @Operation(summary = "Updates the task ", description = "Updates the details of an endpoint with ID ")
    @PutMapping("{id}")
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody TaskRequest request) {
        return taskService.updateTask(id, request);
    }

    @Operation(summary = "Deletes the single task", description = "Delete task with ID")
    @DeleteMapping("{id}")
    public TaskResponse deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }

}
