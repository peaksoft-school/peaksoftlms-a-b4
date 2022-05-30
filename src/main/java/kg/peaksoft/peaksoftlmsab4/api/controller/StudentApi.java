package kg.peaksoft.peaksoftlmsab4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.api.payload.PaginationResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.StudentRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsab4.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/students")
@AllArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Student", description = "The Student CRUD operations")
public class StudentApi {

    private final StudentService studentService;

    @Operation(summary = "Creates new entity: Student", description = "Saves a new student")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR','ADMIN')")
    @PostMapping
    public StudentResponse saveStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    @PostMapping("/import/{groupId}")
    public List<StudentResponse> importExcelFile(@RequestParam("file") MultipartFile files, @PathVariable Long groupId) throws IOException {
        return studentService.importExcel(files, groupId);
    }

    @Operation(summary = "Creates new entity: Student with group", description = "Saves a new student and add him/her to existed group")
    @PostMapping("/withGroup")
    public StudentResponse saveStudentWithGroup(@RequestBody StudentRequest studentRequestDto) {
        return studentService.saveStudentWithGroup(studentRequestDto);
    }

    @Operation(summary = "Gets all existed students", description = "Returns all students in a list ")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR','ADMIN')")
    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudent();
    }

    @Operation(summary = "Gets a single entity by identifier",
            description = "For valid response try integer IDs with value >= 1 ")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR','ADMIN')")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @Operation(summary = "Updates the student ", description = "Updates the details of an endpoint with ID ")
    @PutMapping("/{id}")
    public StudentResponse updateStudent(@PathVariable Long id,
                                         @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

    @Operation(summary = "Deletes the single student", description = "Deletes student by id ")
    @DeleteMapping("/{id}")
    public StudentResponse deleteById(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @Operation(summary = "Assigns student to a group", description = "Adds a student to a group")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR','ADMIN')")
    @PutMapping("accept-to-group")
    public StudentResponse setStudentToGroup(@RequestParam Long studentId, @RequestParam Long groupId) {
        return studentService.setStudentToGroup(groupId, studentId);
    }

    @Operation(summary = "Assign student to a course", description = "Adds a student to a course")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR','ADMIN')")
    @PutMapping("/accept-to-course")
    public StudentResponse setStudentToCourse(@RequestParam Long courseId, @RequestParam Long studentId) {
        return studentService.setStudentToCourse(courseId, studentId);
    }
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR','ADMIN')")
    @GetMapping("/pagination")
    public PaginationResponse<StudentResponse> getStudentPagination(@RequestParam int page, @RequestParam int size, @RequestParam StudyFormat studyFormat){
        return studentService.getStudentPagination(page-1,size,studyFormat);
    }

    @PreAuthorize("hasAnyAuthority('INSTRUCTOR','ADMIN')")
    @GetMapping("/firstname/{name}")
    public List<StudentResponse> finByStudentName(@PathVariable String name){
        return studentService.findByStudentName(name);
    }

}