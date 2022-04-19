package kg.peaksoft.peaksoftlmsab4.api.controller;

import kg.peaksoft.peaksoftlmsab4.api.payload.CourseRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.CourseResponse;
import kg.peaksoft.peaksoftlmsab4.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class CourseApi {

    private final CourseService courseService;

    @PostMapping("/register")
    public CourseResponse saveNewCourse(@RequestBody CourseRequest courseRequest) {
        return courseService.saveCourse(courseRequest);
    }

    @GetMapping
    public List<CourseResponse> findAllCourse() {
        return courseService.findAllCourse();
    }

    @GetMapping("/get/{courseId}")
    public CourseResponse findCourseById(@PathVariable Long courseId) {
        return courseService.getById(courseId);
    }

    @DeleteMapping("/delete/{courseId}")
    public CourseResponse deleteCourse(@PathVariable Long courseId) {
        return courseService.deleteCourseById(courseId);
    }

    @PutMapping("/update/{courseId}")
    public CourseResponse updateCourse(@PathVariable Long courseId,
                                       @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourseById(courseId, courseRequest);
    }
}