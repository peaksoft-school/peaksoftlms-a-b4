package kg.peaksoft.peaksoftlmsab4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.api.payload.CourseRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.CourseResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.InstructorResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
@CrossOrigin(origins = "*",maxAge = 3600)
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin(origins = "*",maxAge = 3600)
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
    @GetMapping("/{id}")
    public CourseResponse findCourseById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @Operation(summary = "Deletes the courses ", description = "Deletes courses by id ")
    @DeleteMapping("/{id}")
    public CourseResponse deleteCourse(@PathVariable Long id) {
        return courseService.deleteCourseById(id);
    }

    @Operation(summary = "Updates the course ", description = "Updates the details of an endpoint with ID ")
    @PutMapping("/{id}")
    public CourseResponse updateCourse(@PathVariable Long id,
                                       @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourseById(id, courseRequest);
    }

    @Operation(summary = "Get students by course id",
            description = "Get all students in this course")
    @GetMapping("/students/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<StudentResponse> getAllStudentByCourseId(@PathVariable Long id) {
        return courseService.getAllStudentsByCourseId(id);
    }

    @Operation(summary = "Get teachers by course id",
            description = "Get all teachers in this course")
    @GetMapping("/teachers/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<InstructorResponse> getAllTeacherByCourseId(@PathVariable Long id) {
        return courseService.getAllTeacherByCourseId(id);
    }
}