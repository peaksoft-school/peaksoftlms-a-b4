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

    @PostMapping("/save")
    public StudentResponse saveStudent(@RequestBody StudentRequest studentRequest){
        return studentService.saveStudent(studentRequest);
    }

    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudent();
    }

    @GetMapping("/get/{studentId}")
    public StudentResponse getStudentById(@PathVariable Long studentId){
        return studentService.getInstructorById(studentId);
    }

    @PutMapping("/update/{studentId}")
    public StudentResponse updateStudent(@PathVariable Long studentId,
                                         @RequestBody StudentRequest studentRequest){
        return studentService.updateStudent(studentId,studentRequest);
    }

    @DeleteMapping("/delete/{studentId}")
    public void deleteById(@PathVariable Long studentId) {
       studentService.deleteStudent(studentId);
    }


}
