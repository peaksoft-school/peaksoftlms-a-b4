package kg.peaksoft.peaksoftlmsab4.api.controller;

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
public class InstructorApi {

    private final InstructorService instructorService;

    @PostMapping
    public InstructorResponse saveInstructor(@RequestBody InstructorRequest instructorRequest) {
        return instructorService.saveInstructor(instructorRequest);
    }

    @GetMapping
    public List<InstructorResponse> getAllInstructors() {
        return instructorService.getAllInstructor();
    }

    @GetMapping("{id}")
    public InstructorResponse getInstructorById(@PathVariable("id") Long instructorId) {
        return instructorService.getInstructorById(instructorId);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
    }

    @PutMapping("{id}")
    public InstructorResponse updateInstructor(@PathVariable Long id,
                                               @RequestBody InstructorRequest instructorRequest) {
        return instructorService.updateInstructor(id, instructorRequest);

    }

    @PutMapping("/{courseId}/set/{instructorId}")
    public InstructorResponse addInstructorToCourse(@PathVariable Long courseId, @PathVariable Long instructorId) {
        return instructorService.addInstructorToCourse(courseId, instructorId);
    }
}

