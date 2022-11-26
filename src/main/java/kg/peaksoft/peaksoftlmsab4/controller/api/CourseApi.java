package kg.peaksoft.peaksoftlmsab4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.controller.payload.request.AssignRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.request.CourseRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.*;
import kg.peaksoft.peaksoftlmsab4.service.CourseService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/courses")
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Course API", description = "Course endpoints")
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

    @Operation(summary = "Gets a single entity by identifier", description = "For valid response try integer IDs with value >= 1 ")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @GetMapping("{id}")
    public CourseResponse findCourseById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @Operation(summary = "Deletes the courses ", description = "Deletes courses by id ")
    @DeleteMapping("{id}")
    public CourseResponse deleteCourse(@PathVariable Long id) {
        return courseService.deleteCourseById(id);
    }

    @Operation(summary = "Updates the course ", description = "Updates the details of an endpoint with ID ")
    @PutMapping("{id}")
    public CourseResponse updateCourse(@PathVariable Long id, @RequestBody CourseRequest request) {
        return courseService.updateCourseById(id, request);
    }

    @Operation(summary = "Get students by course id", description = "Get all students in this course")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @GetMapping("students/{id}")
    public List<StudentResponse> getAllStudentByCourseId(@PathVariable Long id) {
        return courseService.getAllStudentsByCourseId(id);
    }

    @Operation(summary = "Get instructor by course id", description = "Get all instructor in this course")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @GetMapping("instructors/{id}")
    public List<InstructorResponse> getAllInstructorByCourseId(@PathVariable Long id) {
        return courseService.getAllInstructorByCourseId(id);
    }

    @Operation(summary = "Get Lessons by course id", description = "Get all lessons in this course")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @GetMapping("lessons/{id}")
    public List<LessonResponseForGet> getAllLessonByCourseId(@PathVariable Long id) {
        return courseService.getAllLessonByCourseId(id);
    }

    @Operation(summary = "Assign Instructor to Course", description = "Assign Instructor to Course")
    @PostMapping("assign")
    public String assignInstructor(@RequestBody AssignRequest request) {
        return courseService.assignInstructorToCourse(request);
    }

    @GetMapping("pagination")
    public PaginationResponse<CourseResponse> getCoursePagination(@RequestParam int page, @RequestParam int size) {
        return courseService.getCoursePagination(page - 1, size);
    }

}