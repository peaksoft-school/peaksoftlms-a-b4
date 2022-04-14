package kg.peaksoft.peaksoftlmsab4.api;

import kg.peaksoft.peaksoftlmsab4.dto.request.StudentRequest;
import kg.peaksoft.peaksoftlmsab4.dto.response.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
@AllArgsConstructor
public class StudentApi {

    private final StudentService studentService;

    @PostMapping
    public StudentResponse saveStudent(@RequestBody StudentRequest studentRequest){
        return studentService.saveStudent(studentRequest);
    }

    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudent();
    }

    @GetMapping("{id}")
    public StudentResponse getStudentById(@PathVariable Long id){
        return studentService.getInstructorById(id);
    }

    @PutMapping("{id}")
    public StudentResponse updateStudent(@PathVariable Long id,
                                         @RequestBody StudentRequest studentRequest){
        return studentService.updateStudent(id,studentRequest);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
       studentService.deleteStudent(id);
    }


}
