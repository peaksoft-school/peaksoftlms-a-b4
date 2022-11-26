package kg.peaksoft.peaksoftlmsab4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.controller.payload.request.LessonRequest;
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

    @Operation(summary = "Creates new entity: Lesson", description = "Saves a new lesson")
    @PostMapping("{courseId}")
    public LessonResponse addLesson(@RequestBody LessonRequest request, @PathVariable Long courseId) {
        return lessonService.create(request, courseId);
    }

    @Operation(summary = "Gets all existed lessons", description = "Returns all lessons in a list ")
    @GetMapping
    public List<LessonResponseForGet> getAllLessons() {
        return lessonService.getAll();
    }

    @Operation(summary = "Gets a single entity by identifier", description = "For valid response try integer IDs with value >= 1 ")
    @GetMapping("{id}")
    public LessonResponseForGet getLessonById(@PathVariable Long id) {
        return lessonService.getById(id);
    }

    @Operation(summary = "Updates the lesson ", description = "Updates the details of an endpoint with ID ")
    @PutMapping("{id}")
    public LessonResponse updateLesson(@PathVariable Long id, @RequestBody LessonRequest request) {
        return lessonService.update(id, request);
    }

    @Operation(summary = "Deletes the lesson ", description = "Deletes lesson by id ")
    @DeleteMapping("{id}")
    public LessonResponse deleteById(@PathVariable Long id) {
        return lessonService.deleteById(id);
    }

}
