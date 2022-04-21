package kg.peaksoft.peaksoftlmsab4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.api.payload.InstructorRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.InstructorResponse;
import kg.peaksoft.peaksoftlmsab4.service.InstructorService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/instructors")
@AllArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Instructor", description = "The Instructor CRUD operations")
public class InstructorApi {

    private final InstructorService instructorService;

    @Operation(summary = "Creates new entity: Instructor", description = "Saves a new user Instructor")
    @PostMapping
    public InstructorResponse saveInstructor(@RequestBody InstructorRequest instructorRequest) {
        return instructorService.saveInstructor(instructorRequest);
    }

    @Operation(summary = "Gets all existed teachers", description = "Returns all teachers in a list ")
    @GetMapping
    public List<InstructorResponse> getAllInstructors() {
        return instructorService.getAllInstructor();
    }

    @Operation(summary = "Gets a single entity by identifier",
            description = "For valid response try integer IDs with value >= 1 ")
    @GetMapping("{id}")
    public InstructorResponse getInstructorById(@PathVariable("id") Long instructorId) {
        return instructorService.getInstructorById(instructorId);
    }

    @Operation(summary = "Deletes the user: instructor", description = "Deletes user instructor by id ")
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
    }

    @Operation(summary = "Updates the instructor ", description = "Updates the details of an endpoint with ID ")
    @PutMapping("{id}")
    public InstructorResponse updateInstructor(@PathVariable Long id,
                                               @RequestBody InstructorRequest instructorRequest) {
        return instructorService.updateInstructor(id, instructorRequest);

    }

    @Operation(summary = "Assign teacher to a course", description = "Adds a teacher to a course")
    @PutMapping("/{courseId}/set/{instructorId}")
    public InstructorResponse addInstructorToCourse(@PathVariable Long courseId, @PathVariable Long instructorId) {
        return instructorService.addInstructorToCourse(courseId, instructorId);
    }
}

