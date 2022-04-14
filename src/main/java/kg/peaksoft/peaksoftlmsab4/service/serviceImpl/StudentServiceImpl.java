package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.dto.mapper.edit.StudentEditMapper;
import kg.peaksoft.peaksoftlmsab4.dto.mapper.view.StudentViewMapper;
import kg.peaksoft.peaksoftlmsab4.dto.request.StudentRequest;
import kg.peaksoft.peaksoftlmsab4.dto.response.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.entity.Student;
import kg.peaksoft.peaksoftlmsab4.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsab4.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentEditMapper studentEditMapper;
    private final StudentViewMapper studentViewMapper;
    private final StudentRepository studentRepository;

    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        return studentViewMapper
                .convertToStudentResponse(studentRepository
                        .save(studentEditMapper
                                .convertToStudent(studentRequest)));
    }

    @Override
    public List<StudentResponse> getAllStudent() {
        return studentRepository.findAll()
                .stream()
                .map(studentViewMapper::convertToStudentResponse)
                .toList();
    }

    @Override
    public StudentResponse getInstructorById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        return studentViewMapper.convertToStudentResponse(student);
    }

    @Override
    public StudentResponse updateStudent(Long studentId, StudentRequest studentRequest) {
        Student student = studentRepository.findById(studentId).orElseThrow();

        String currentName = student.getFirstName();
        String newName = studentRequest.getFirstName();

        if (!currentName.equals(newName)) {
            student.setFirstName(newName);
        }

        String currentLastName = student.getLastName();
        String newLastName = studentRequest.getLastName();

        if (!currentLastName.equals(newLastName)) {
            student.setLastName(newLastName);
        }

        String currentMobilePhone = student.getMobilePhone();
        String newMobilePhone = studentRequest.getMobilePhone();

        if (!currentMobilePhone.equals(newMobilePhone)) {
            student.setMobilePhone(newMobilePhone);
        }

        if (!student.getStudyFormat().equals(studentRequest.getStudyFormat())) {
            student.setStudyFormat(studentRequest.getStudyFormat());
        }

        return studentViewMapper.convertToStudentResponse(student);
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

}
