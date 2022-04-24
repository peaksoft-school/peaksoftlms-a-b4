package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.StudentRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.GroupEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.ResponseEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.StudentEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.StudentViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsab4.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsab4.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsab4.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final StudentEditMapper studentEditMapper;
    private final StudentViewMapper studentViewMapper;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    @Override
    public ResponseEntity saveStudent(StudentRequest studentRequest) {
        String email = studentRequest.getEmail();
        checkEmail(email);
        StudentEntity student = studentRepository.save(studentEditMapper
                .convertToStudent(studentRequest));
        log.info("Student with name : {} has successfully saved to database", student.getFirstName());
        return ResponseEntity.builder()
                .httpStatus(CREATED)
                .message(String.format("Student with name : %s has successfully saved to database", student.getFirstName()))
                .build();
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
        StudentEntity student = getByIdMethod(studentId);
        return studentViewMapper.convertToStudentResponse(student);
    }

    @Override
    public ResponseEntity updateStudent(Long studentId, StudentRequest studentRequest) {
        StudentEntity student = getByIdMethod(studentId);
        studentEditMapper.updateStudent(student, studentRequest);
        StudentEntity savedStudent = studentRepository.save(student);
        log.info("Student with name : {} has successfully updated", savedStudent.getFirstName());
        return ResponseEntity.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Student with name : %s has successfully updated", savedStudent.getFirstName()))
                .build();
    }

    @Override
    public ResponseEntity deleteStudent(Long studentId) {
        boolean existById = studentRepository.existsById(studentId);
        if (!existById) {
            log.error("Student with id = {} does not exists, you can not delete it", studentId);
            throw new BadRequestException(
                    String.format("Student with id = %s does not exists, you can not delete it", studentId)
            );
        }
        studentRepository.deleteById(studentId);
        log.info("Student with id = {} has successfully deleted", studentId);

        return ResponseEntity.builder()
                .httpStatus(HttpStatus.MOVED_PERMANENTLY)
                .message(String.format("Student with id = %d has successfully deleted", studentId))
                .build();
    }

    @Override
    public ResponseEntity setStudentToGroup(Long groupId, Long studentId) {
        GroupEntity group = groupRepository.findById(groupId)
                .orElseThrow(() -> {
                    log.error("Group with id = {} does not exists", groupId);
                    throw new NotFoundException(
                            String.format("Group with id = %s does not exists", groupId)
                    );
                });
        StudentEntity student = getByIdMethod(studentId);
        student.setGroup(group);
        log.info("Student with id = {} has successfully added to group with id = {}", studentId, groupId);
        return ResponseEntity.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Student with id = %d has successfully added to group with id = %d", studentId, groupId))
                .build();
    }

    @Override
    public ResponseEntity setStudentToCourse(Long courseId, Long studentId) {
        CourseEntity course = courseRepository.findById(courseId)
                .orElseThrow(() -> {
                    log.error("Course with id = {} does not exists", courseId);
                    throw new NotFoundException(
                            String.format("Course with id = %s does not exists", courseId)
                    );
                });
        StudentEntity student = getByIdMethod(studentId);
        student.setCourse(course);
        log.info("Student with id = {} has successfully added to course with id = {}", studentId, courseId);
        return ResponseEntity.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Student with id = %d has successfully added to course with id = %d", studentId, courseId))
                .build();
    }

    @Override
    public ResponseEntity saveStudentWithGroup(Long groupId, StudentRequest studentRequest) {
        GroupEntity group = groupRepository.findById(groupId)
                .orElseThrow(() -> {
                    log.error("Group with id = {} does not exists", groupId);
                    throw new NotFoundException(
                            String.format("Group with id = %s does not exists", groupId)
                    );
                });

        String email = studentRequest.getEmail();
        checkEmail(email);

        StudentEntity convertedStudent = studentEditMapper.convertToStudent(studentRequest);
        convertedStudent.setGroup(group);

        StudentEntity savedStudent = studentRepository.save(convertedStudent);

        log.info("Student with name = {} successfully saved to database", savedStudent.getFirstName());

        return ResponseEntity.builder()
                .httpStatus(CREATED)
                .message(String.format("Student with name : %s has successfully saved to database", savedStudent.getFirstName()))
                .build();
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

    private StudentEntity getByIdMethod(Long studentId) {
        return studentRepository.findById(studentId).
                orElseThrow(() -> {
                    log.error("Student with id = {} does not exists", studentId);
                    throw new NotFoundException(
                            String.format("Student with id = %s does not exists", studentId)
                    );
                });
    }
}
