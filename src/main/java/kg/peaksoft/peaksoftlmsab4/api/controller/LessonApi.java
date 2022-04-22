package kg.peaksoft.peaksoftlmsab4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.api.payload.LessonRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.LessonResponse;
import kg.peaksoft.peaksoftlmsab4.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lessons")
@PreAuthorize("hasAuthority('ADMIN')")
@AllArgsConstructor
@Tag(name = "Lesson", description = "The Lesson CRUD operations")
public class LessonApi {

    private final LessonService lessonService;

    @PostMapping("{courseId}")
    @Operation(summary = "Creates new entity: Lesson", description = "Saves a new lesson")
    public LessonResponse addLesson(@RequestBody LessonRequest lessonRequest, @PathVariable Long courseId) {
        return lessonService.addLesson(lessonRequest, courseId);
    }

    @GetMapping
    @Operation(summary = "Gets all existed lessons", description = "Returns all lessons in a list ")
    public List<LessonResponse> getAllLessons() {
        return lessonService.getAllStudent();
    }

    @GetMapping("{lessonId}")
    @Operation(summary = "Gets a single entity by identifier",
            description = "For valid response try integer IDs with value >= 1 ")
    public LessonResponse getLessonById(@PathVariable Long lessonId) {
        return lessonService.getStudentById(lessonId);
    }

    @PutMapping("{lessonId}")
    @Operation(summary = "Updates the lesson ", description = "Updates the details of an endpoint with ID ")
    public LessonResponse updateLesson(@PathVariable Long lessonId,
                                       @RequestBody LessonRequest lessonRequest) {
        return lessonService.updateStudent(lessonId, lessonRequest);
    }

    @DeleteMapping("{lessonId}")
    @Operation(summary = "Deletes the lesson ", description = "Deletes lesson by id ")
    public void deleteById(@PathVariable Long lessonId) {
        lessonService.deleteStudent(lessonId);
    }
}
