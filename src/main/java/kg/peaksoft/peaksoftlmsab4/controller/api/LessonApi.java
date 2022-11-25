package kg.peaksoft.peaksoftlmsab4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.controller.payload.LessonRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.LessonResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.LessonResponseForGet;
import kg.peaksoft.peaksoftlmsab4.service.LessonService;
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
@RequestMapping("api/lessons")
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Lesson API", description = "Lesson endpoints")
public class LessonApi {

    private final LessonService lessonService;

    @PostMapping("/{courseId}")
    @Operation(summary = "Creates new entity: Lesson", description = "Saves a new lesson")
    public LessonResponse addLesson(@RequestBody LessonRequest lessonRequest, @PathVariable Long courseId) {
        return lessonService.create(lessonRequest, courseId);
    }

    @CrossOrigin
    @GetMapping
    @Operation(summary = "Gets all existed lessons", description = "Returns all lessons in a list ")
    public List<LessonResponseForGet> getAllLessons() {
        return lessonService.getAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @Operation(summary = "Gets a single entity by identifier",
            description = "For valid response try integer IDs with value >= 1 ")
    public LessonResponseForGet getLessonById(@PathVariable Long id) {
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
