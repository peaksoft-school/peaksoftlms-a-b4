package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.StudentRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.GroupEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.StudentEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.StudentViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsab4.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsab4.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final StudentEditMapper studentEditMapper;
    private final StudentViewMapper studentViewMapper;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        String email = studentRequest.getEmail();
        checkEmail(email);
        StudentEntity student = studentRepository.save(studentEditMapper
                .convertToStudent(studentRequest));
        log.info(" Student with name : {} has successfully saved to database", student.getFirstName());
        return studentViewMapper.convertToStudentResponse(student);
    }

    @Override
    public List<StudentResponse> getAllStudent() {
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (StudentEntity student : studentRepository.findAll()) {
            studentResponses.add(studentViewMapper.convertToStudentResponse(student));
        }
        log.info("Found {} instructors ", studentResponses.size());
        return studentResponses;
    }

    @Override
    public StudentResponse getStudentById(Long studentId) {
        StudentEntity student = studentRepository.findById(studentId).
                orElseThrow(() -> {
                    log.error("Student with id = {} does not exists", studentId);
                    throw new NotFoundException(
                            String.format("Student with id = %s does not exists", studentId)
                    );
                });
        return studentViewMapper.convertToStudentResponse(student);
    }

    @Override
    public StudentResponse updateStudent(Long studentId, StudentRequest studentRequest) {
        StudentEntity student = studentRepository.findById(studentId).
                orElseThrow(() -> {
                    log.error("Student with id = {} does not exists", studentId);
                    throw new NotFoundException(
                            String.format("Student with id = %s does not exists", studentId)
                    );
                });
        studentEditMapper.updateStudent(student, studentRequest);
        return studentViewMapper.convertToStudentResponse(studentRepository.save(student));
    }

    @Override
    public void deleteStudent(Long studentId) {
        boolean existById = studentRepository.existsById(studentId);
        if (!existById) {
            log.error("Student with id = {} does not exists, you can not delete it", studentId);
            throw new BadRequestException(
                    String.format("Student with id = %s does not exists, you can not delete it", studentId)
            );
        }
        studentRepository.deleteById(studentId);
    }

    @Override
    public StudentResponse setStudentToGroup(Long groupId, Long studentId) {
        GroupEntity group = groupRepository.getById(groupId);
        StudentEntity student = studentRepository.getById(studentId);
        student.setGroup(group);
        group.setStudent(student);
        return studentViewMapper.convertToStudentResponse(studentRepository.save(student));
    }

    @Override
    public Page<StudentEntity> findAllStudentWithPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }

    private void checkEmail(String email) {
        boolean exists = studentRepository.existsByEmail(email);
        if (exists) {
            log.info("Student with email = {} already exists", email);
            throw new BadRequestException(
                    "Student with email = " + email + " already exists"
            );
        }
    }
}
