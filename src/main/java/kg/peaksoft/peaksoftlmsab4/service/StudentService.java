package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.dto.request.StudentRequest;
import kg.peaksoft.peaksoftlmsab4.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse saveStudent(StudentRequest studentRequest);

    List<StudentResponse> getAllStudent();

    StudentResponse getInstructorById(Long studentId);

    StudentResponse updateStudent(Long studentId, StudentRequest studentRequest);

    void deleteStudent(Long studentId);
}
