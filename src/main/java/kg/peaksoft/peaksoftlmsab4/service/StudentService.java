package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.StudentRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.ResponseEntity;

import java.util.List;

public interface StudentService {

    ResponseEntity saveStudent(StudentRequest studentRequest);

    List<StudentResponse> getAllStudent();

    StudentResponse getStudentById(Long studentId);

    ResponseEntity updateStudent(Long studentId, StudentRequest studentRequest);

    ResponseEntity deleteStudent(Long studentId);

    ResponseEntity setStudentToGroup(Long groupId, Long studentId);

    ResponseEntity setStudentToCourse(Long courseId, Long studentId);

    ResponseEntity saveStudentWithGroup(Long groupId, StudentRequest studentRequestDto);
}
