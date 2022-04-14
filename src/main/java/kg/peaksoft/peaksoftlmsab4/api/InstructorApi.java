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

    @PostMapping("/save")
    public InstructorResponse saveInstructor(@RequestBody InstructorRequest instructorRequest) {
        return instructorService.saveInstructor(instructorRequest);
    }

    @GetMapping
    public List<InstructorResponse> getAllInstructors() {
        return instructorService.getAllInstructor();
    }

    @GetMapping("/get/{instructorId}")
    public InstructorResponse getInstructorById(@PathVariable("instructorId") Long instructorId) {
        return instructorService.getInstructorById(instructorId);
    }

    @DeleteMapping("/delete/{instructorId}")
    public void deleteById(@PathVariable Long instructorId) {
        instructorService.deleteInstructor(instructorId);
    }

    @PutMapping("/update/{instructorId}")
    public InstructorResponse updateInstructor(@PathVariable Long instructorId,
                                               @RequestBody InstructorRequest instructorRequest) {
        return instructorService.updateInstructor(instructorId, instructorRequest);

    }

}
