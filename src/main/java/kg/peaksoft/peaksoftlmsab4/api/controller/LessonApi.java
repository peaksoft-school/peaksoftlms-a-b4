package kg.peaksoft.peaksoftlmsab4.api.controller;

import kg.peaksoft.peaksoftlmsab4.api.payload.LessonRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.LessonResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lessons")
@PreAuthorize("hasAuthority('ADMIN')")
@AllArgsConstructor
public class LessonApi {

    private final LessonService lessonService;

    @PostMapping("{courseId}")
    public LessonResponse addLesson(@RequestBody LessonRequest lessonRequest,@PathVariable Long courseId){
        return lessonService.addLesson(lessonRequest,courseId);
    }
    @GetMapping
    public List<LessonResponse> getAllLessons() {
        return lessonService.getAllStudent();
    }

    @GetMapping("{lessonId}")
    public LessonResponse getLessonById(@PathVariable Long lessonId) {
        return lessonService.getStudentById(lessonId);
    }

    @PutMapping("{lessonId}")
    public LessonResponse updateLesson(@PathVariable Long lessonId,
                                       @RequestBody LessonRequest lessonRequest) {
        return lessonService.updateStudent(lessonId, lessonRequest);
    }

    @DeleteMapping("{lessonId}")
    public void deleteById(@PathVariable Long lessonId) {
        lessonService.deleteStudent(lessonId);
    }
}
