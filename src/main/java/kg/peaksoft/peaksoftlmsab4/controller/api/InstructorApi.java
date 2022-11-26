package kg.peaksoft.peaksoftlmsab4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.controller.payload.CourseResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.request.InstructorRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.InstructorResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.PaginationResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
@RequestMapping("api/instructors")
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Instructor API", description = "Instructor endpoints")
public class InstructorApi {

    private final InstructorService instructorService;

    @Operation(summary = "Creates new entity: Instructor", description = "Saves a new user Instructor")
    @PostMapping
    public InstructorResponse saveInstructor(@RequestBody InstructorRequest request) {
        return instructorService.saveInstructor(request);
    }

    @Operation(summary = "Gets all existed instructors", description = "Returns all instructors in a list ")
    @GetMapping
    public List<InstructorResponse> getAllInstructors() {
        return instructorService.getAllInstructor();
    }

    @Operation(summary = "Gets a single entity by identifier", description = "For valid response try integer IDs with value >= 1 ")
    @GetMapping("{id}")
    public InstructorResponse getInstructorById(@PathVariable Long id) {
        return instructorService.getInstructorById(id);
    }

    @Operation(summary = "Deletes the user: instructor", description = "Deletes user instructor by id ")
    @DeleteMapping("{id}")
    public InstructorResponse deleteById(@PathVariable Long id) {
        return instructorService.deleteInstructor(id);
    }

    @Operation(summary = "Updates the instructor ", description = "Updates the details of an endpoint with ID ")
    @PutMapping("{id}")
    public InstructorResponse updateInstructor(@PathVariable Long id, @RequestBody InstructorRequest request) {
        return instructorService.updateInstructor(id, request);
    }

    @Operation(summary = "Assign instructor to a course", description = "Adds a instructor to a course")
    @PutMapping("accept-to-course")
    public InstructorResponse addInstructorToCourse(@RequestParam Long courseId, @RequestParam Long instructorId) {
        return instructorService.addInstructorToCourse(courseId, instructorId);
    }

    @Operation(summary = "Assign teacher to course", description = "This endpoint for adding a teacher to a course. Only user with role admin can add teacher to course")
    @PutMapping("accept-list-to-course")
    public CourseResponse assignManyInstructorsToCourse(@RequestParam Long courseId, @RequestParam List<Long> instructorId) {
        return instructorService.assignInstructorsToCourse(courseId, instructorId);
    }

    @Operation(summary = "Get instructor's courses", description = "Get all courses of instructor by authentication")
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @GetMapping("courses")
    public List<CourseResponse> getInstructorCourse(Authentication authentication) {
        AuthInfo authInfo = (AuthInfo) authentication.getPrincipal();
        return instructorService.getInstructorsCourses(authInfo.getEmail());
    }

    @GetMapping("pagination")
    public PaginationResponse<InstructorResponse> getCoursePagination(@RequestParam int page, @RequestParam int size) {
        return instructorService.getInstructorPagination(page - 1, size);
    }

}

