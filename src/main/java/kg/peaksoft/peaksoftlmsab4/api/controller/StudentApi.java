package kg.peaksoft.peaksoftlmsab4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.api.payload.StudentRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
@AllArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Student", description = "The Student CRUD operations")
public class StudentApi {

    private final StudentService studentService;

    @Operation(summary = "Creates new entity: Student", description = "Saves a new student")
    @PostMapping
    public StudentResponse saveStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    @Operation(summary = "Gets all existed students", description = "Returns all students in a list ")
    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudent();
    }

    @Operation(summary = "Gets a single entity by identifier",
            description = "For valid response try integer IDs with value >= 1 ")
    @GetMapping("{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @Operation(summary = "Updates the student ", description = "Updates the details of an endpoint with ID ")
    @PutMapping("{id}")
    public StudentResponse updateStudent(@PathVariable Long id,
                                         @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

    @Operation(summary = "Deletes the single student", description = "Deletes student by id ")
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @Operation(summary = "Assigns student to a group", description = "Adds a student to a group")
    @PutMapping("/{groupId}/setGroup/{studentId}")
    public StudentResponse setStudentToGroup(@PathVariable Long groupId, @PathVariable Long studentId) {
        return studentService.setStudentToGroup(groupId, studentId);
    }

    @Operation(summary = "Assign student to a course", description = "Adds a student to a course")
    @PutMapping("/{courseId}/setCourse/{studentId}")
    public StudentResponse setStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        return studentService.setStudentToCourse(courseId, studentId);
    }
}