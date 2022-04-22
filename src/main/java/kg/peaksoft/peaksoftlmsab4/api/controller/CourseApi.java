package kg.peaksoft.peaksoftlmsab4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.api.payload.CourseRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.CourseResponse;
import kg.peaksoft.peaksoftlmsab4.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Course", description = "The Course CRUD operations")
public class CourseApi {

    private final CourseService courseService;

    @Operation(summary = "Creates new entity: Course", description = "Saves a new course")
    @PostMapping
    public CourseResponse saveNewCourse(@RequestBody CourseRequest courseRequest) {
        return courseService.saveCourse(courseRequest);
    }

    @Operation(summary = "Gets all existed courses", description = "Returns all courses in a list ")
    @GetMapping
    public List<CourseResponse> findAllCourse() {
        return courseService.findAllCourse();
    }

    @Operation(summary = "Gets a single entity by identifier",
            description = "For valid response try integer IDs with value >= 1 ")
    @GetMapping("/{courseId}")
    public CourseResponse findCourseById(@PathVariable Long courseId) {
        return courseService.getById(courseId);
    }

    @Operation(summary = "Deletes the courses ", description = "Deletes courses by id ")
    @DeleteMapping("/{courseId}")
    public CourseResponse deleteCourse(@PathVariable Long courseId) {
        return courseService.deleteCourseById(courseId);
    }

    @Operation(summary = "Updates the course ", description = "Updates the details of an endpoint with ID ")
    @PutMapping("/{courseId}")
    public CourseResponse updateCourse(@PathVariable Long courseId,
                                       @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourseById(courseId, courseRequest);
    }
}