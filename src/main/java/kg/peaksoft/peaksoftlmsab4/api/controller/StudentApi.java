package kg.peaksoft.peaksoftlmsab4.api.controller;

import kg.peaksoft.peaksoftlmsab4.api.payload.StudentRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import kg.peaksoft.peaksoftlmsab4.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
@AllArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class StudentApi {

    private final StudentService studentService;

    @PostMapping
    public StudentResponse saveStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudent();
    }

    @GetMapping("{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("{id}")
    public StudentResponse updateStudent(@PathVariable Long id,
                                         @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @PutMapping("/{groupId}/set/{studentId}")
    public StudentResponse setStudentToGroup(@PathVariable Long groupId, @PathVariable Long studentId) {
        return studentService.setStudentToGroup(groupId, studentId);
    }

    @GetMapping("/getPage/{page}/{size}")
    public Page<StudentEntity> findAllWithPage(@PathVariable int page,
                                               @PathVariable int size) {
        return studentService.findAllStudentWithPage(page, size);
    }
}