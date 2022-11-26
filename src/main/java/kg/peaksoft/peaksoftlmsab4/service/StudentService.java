package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.PaginationResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.request.StudentRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.model.enums.StudyFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentService {

    StudentResponse saveStudent(StudentRequest studentRequest);

    List<StudentResponse> getAllStudent();

    StudentResponse getStudentById(Long studentId);

    StudentResponse updateStudent(Long studentId, StudentRequest studentRequest);

    StudentResponse deleteStudent(Long studentId);

    StudentResponse setStudentToGroup(Long groupId, Long studentId);

    StudentResponse setStudentToCourse(Long courseId, Long studentId);

    StudentResponse saveStudentWithGroup(StudentRequest studentRequestDto);

    List<StudentResponse> importExcel(MultipartFile file,Long groupId) throws IOException;

    PaginationResponse<StudentResponse> getStudentPagination(int page, int size, StudyFormat studyFormat);

    List<StudentResponse> findByStudentName(String name);
}
