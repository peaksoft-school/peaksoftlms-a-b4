package kg.peaksoft.peaksoftlmsab4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.controller.payload.*;
import kg.peaksoft.peaksoftlmsab4.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
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
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
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

    @Operation(summary = "Get instructor by course id",
            description = "Get all instructor in this course")
    @GetMapping("/instructors/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<InstructorResponse> getAllInstructorByCourseId(@PathVariable Long id) {
        return courseService.getAllInstructorByCourseId(id);
    }
    @Operation(summary = "Get Lessons by course id",
    description = "Get all lessons in this course")
    @GetMapping("/lessons/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public  List<LessonResponseForGet> getAllLessonByCourseId(@PathVariable Long id){
        return courseService.getAllLessonByCourseId(id);
    }

    @Operation(summary = "Assign Instructor to Course", description = "Assign Instructor to Course")
    @PostMapping("/assign")
    public String assignInstructor(@RequestBody AssignRequest assignRequest) {

        return courseService.assignInstructorToCourse(assignRequest);
    }

    @GetMapping("/pagination")
    public PaginationResponse<CourseResponse> getCoursePagination(@RequestParam int page, @RequestParam int size) {
        return courseService.getCoursePagination(page - 1, size);
    }
}