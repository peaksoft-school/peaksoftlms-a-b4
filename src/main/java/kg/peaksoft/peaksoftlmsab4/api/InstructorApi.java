package kg.peaksoft.peaksoftlmsab4.api;

import kg.peaksoft.peaksoftlmsab4.dto.request.InstructorRequest;
import kg.peaksoft.peaksoftlmsab4.dto.response.InstructorResponse;
import kg.peaksoft.peaksoftlmsab4.service.InstructorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/instructors")
@AllArgsConstructor
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

}
