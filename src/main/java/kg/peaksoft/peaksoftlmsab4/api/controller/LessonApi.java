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
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@AllArgsConstructor
@CrossOrigin(origins = "*",maxAge = 3600)
@Tag(name = "Lesson", description = "The Lesson CRUD operations")
public class LessonApi {

    private final LessonService lessonService;

    @PostMapping("/{courseId}")
    @Operation(summary = "Creates new entity: Lesson", description = "Saves a new lesson")
    public LessonResponse addLesson(@RequestBody LessonRequest lessonRequest, @PathVariable Long courseId) {
        return lessonService.create(lessonRequest, courseId);
    }

    @GetMapping
    @Operation(summary = "Gets all existed lessons", description = "Returns all lessons in a list ")
    public List<LessonResponse> getAllLessons() {
        return lessonService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a single entity by identifier",
            description = "For valid response try integer IDs with value >= 1 ")
    public LessonResponse getLessonById(@PathVariable Long id) {
        return lessonService.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates the lesson ", description = "Updates the details of an endpoint with ID ")
    public LessonResponse updateLesson(@PathVariable Long id,
                                       @RequestBody LessonRequest lessonRequest) {
        return lessonService.update(id, lessonRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes the lesson ", description = "Deletes lesson by id ")
    public LessonResponse deleteById(@PathVariable Long id) {
        return lessonService.deleteById(id);
    }
}
