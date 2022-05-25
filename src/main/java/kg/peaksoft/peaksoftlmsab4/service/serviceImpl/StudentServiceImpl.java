package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.PaginationResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.StudentRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.exception.AlreadyExistsException;
import kg.peaksoft.peaksoftlmsab4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.exception.InvalidArgumentException;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.*;
import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
import kg.peaksoft.peaksoftlmsab4.model.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsab4.model.mapper.StudentEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.StudentViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsab4.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsab4.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsab4.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;


    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        String email = studentRequest.getEmail();
        if (!validator.patternMatches(email)) {
            throw new InvalidArgumentException(email + " is not valid");
        } else if (!validator.isValid(studentRequest.getPhoneNumber())) {
            throw new InvalidArgumentException(studentRequest.getPhoneNumber() + " is not valid");
        }
        checkEmail(email);
        StudentEntity student = studentRepository.save(studentEditMapper
                .convertToStudent(studentRequest));
        log.info("Student with name : {} has successfully saved to database", student.getFirstName());
        return studentViewMapper.convertToStudentResponse(student);
    }

    @Override
    public List<StudentResponse> getAllStudent() {
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (StudentEntity student : studentRepository.findAll()) {
            studentResponses.add(studentViewMapper.convertToStudentResponse(student));
        }
        log.info("Found {} students ", studentResponses.size());
        return studentResponses;
    }

    @Override
    public StudentResponse getStudentById(Long studentId) {
        StudentEntity student = getByIdMethod(studentId);
        return studentViewMapper.convertToStudentResponse(student);
    }

    @Override
    public StudentResponse updateStudent(Long studentId, StudentRequest studentRequest) {
        StudentEntity student = getByIdMethod(studentId);
        String email = studentRequest.getEmail();
        String entityEmail = student.getAuthInfo().getEmail();
        if (!validator.patternMatches(email)) {
            throw new InvalidArgumentException(email + " is not valid");
        } else if (!validator.isValid(studentRequest.getPhoneNumber())){
            throw new InvalidArgumentException(studentRequest.getPhoneNumber() + " is not valid");
        }
        if (!email.equals(entityEmail)) {
            checkEmail(email);
        }

        String encoderPassword = passwordEncoder.encode(studentRequest.getPassword());
        studentRequest.setPassword(encoderPassword);
        GroupEntity groupEntity = groupRepository.findById(studentRequest.getGroupId())
                .orElseThrow(() -> {
                    log.error("Group with id = {} does not exists", studentRequest.getGroupId());
                    throw new NotFoundException(
                            String.format("Group with id = %s does not exists", studentRequest.getGroupId())
                    );
                });
        studentEditMapper.updateStudent(student, studentRequest);
        student.setGroup(groupEntity);

        StudentEntity savedStudent = studentRepository.save(student);
        log.info("Student with name : {} has successfully updated", savedStudent.getFirstName());
        return studentViewMapper.convertToStudentResponse(savedStudent);
    }

    @Override
    public StudentResponse deleteStudent(Long studentId) {
        boolean existById = studentRepository.existsById(studentId);
        if (!existById) {
            log.error("Student with id = {} does not exists, you can not delete it", studentId);
            throw new BadRequestException(
                    String.format("Student with id = %s does not exists, you can not delete it", studentId)
            );
        }
        StudentEntity studentEntity = getByIdMethod(studentId);
        studentRepository.deleteById(studentId);
        log.info("Student with id = {} has successfully deleted", studentId);

        return studentViewMapper.convertToStudentResponse(studentEntity);
    }

    @Override
    public StudentResponse setStudentToGroup(Long groupId, Long studentId) {
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
        return studentViewMapper.convertToStudentResponse(studentRepository.save(student));
    }

    @Override
    public StudentResponse setStudentToCourse(Long courseId, Long studentId) {
        CourseEntity course = courseRepository.findById(courseId)
                .orElseThrow(() -> {
                    log.error("Course with id = {} does not exists", courseId);
                    throw new NotFoundException(
                            String.format("Course with id = %s does not exists", courseId)
                    );
                });
        StudentEntity student = getByIdMethod(studentId);
        for (StudentEntity studentEntity: course.getStudents()) {
            if (student.getId().equals(studentEntity.getId())) {
                throw new AlreadyExistsException(studentEntity.getFirstName() + " already assigned to course " + course.getCourseName());
            }
        }
        student.setCourse(course);
        log.info("Student with id = {} has successfully added to course with id = {}", studentId, courseId);
        return studentViewMapper.convertToStudentResponse(studentRepository.save(student));
    }

    @Override
    public StudentResponse saveStudentWithGroup(StudentRequest studentRequest) {
        GroupEntity group = groupRepository.findById(studentRequest.getGroupId())
                .orElseThrow(() -> {
                    log.error("Group with id = {} does not exists", studentRequest.getGroupId());
                    throw new NotFoundException(
                            String.format("Group with id = %s does not exists", studentRequest.getGroupId())
                    );
                });
        String email = studentRequest.getEmail();
        if (!validator.patternMatches(email)) {
            throw new InvalidArgumentException(email + " is not valid");
        } else if (!validator.isValid(studentRequest.getPhoneNumber())){
            throw new InvalidArgumentException(studentRequest.getPhoneNumber() + " is not valid");
        }
        checkEmail(email);

        StudentEntity convertedStudent = studentEditMapper.convertToStudent(studentRequest);
        convertedStudent.setGroup(group);

        StudentEntity savedStudent = studentRepository.save(convertedStudent);

        log.info("Student with name = {} successfully saved to database", savedStudent.getFirstName());

        return studentViewMapper.convertToStudentResponse(savedStudent);
    }

    @Override
    public List<StudentResponse> importExcel(MultipartFile files, Long groupId) throws IOException {

        List<StudentEntity> students = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet wordSheet = workbook.getSheetAt(0);

        for (int index = 0; index < wordSheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                StudentEntity student = new StudentEntity();
                XSSFRow row = wordSheet.getRow(index);

                if(row.getCell(0)==null){
                    throw new BadRequestException("FirstName in line index "+index+" is empty!");
                }else {
                    student.setFirstName(row.getCell(0).getStringCellValue());
                }

                if(row.getCell(1)==null){
                    throw new BadRequestException("LastName in line index "+index+" is empty!");
                }else {
                    student.setLastName(row.getCell(1).getStringCellValue());
                }

                if(row.getCell(2)==null){
                    throw new BadRequestException("Phone number in line index "+index+" is empty!");
                }else {
                    student.setPhoneNumber(String.valueOf((int) row.getCell(2).getNumericCellValue()));
                }

                if(row.getCell(3,Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)==null){
                    throw new BadRequestException("Study format in line index "+index+" is empty!");
                }else {
                    student.setStudyFormat(StudyFormat.valueOf(row.getCell(3).getStringCellValue()));
                }

                AuthInfo authInfo = new AuthInfo();

                if(row.getCell(4,Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)==null){
                    throw new BadRequestException("Email in line index "+index+" is empty!");
                }else {
                    authInfo.setEmail(row.getCell(4).getStringCellValue());
                }

                if(row.getCell(5)==null){
                    throw new BadRequestException("Role in line index "+index+" is empty!");
                }else {
                    authInfo.setRole(Role.valueOf(row.getCell(5).getStringCellValue()));
                }

                if(row.getCell(6)==null){
                    throw new BadRequestException("Password in line index "+index+" is empty!");
                }else {
                    authInfo.setPassword(passwordEncoder.encode(String.valueOf(row.getCell(6).getStringCellValue())));
                }

                String email = authInfo.getEmail();
                checkEmail(email);

                student.setAuthInfo(authInfo);
                students.add(student);
            }
        }

        for (StudentEntity student : students) {
            GroupEntity groupEntity = groupRepository.getById(groupId);
            student.setGroup(groupEntity);
            studentRepository.save(student);
        }

        List<StudentResponse> studentResponses = new ArrayList<>();
        for (StudentEntity student : studentRepository.findAll()) {
            studentResponses.add(studentViewMapper.convertToStudentResponse(student));
        }
        log.info("Found {} students ", studentResponses.size());
        return studentResponses;
    }

    @Override
    public PaginationResponse<StudentResponse> getStudentPagination(int page, int size, StudyFormat studyFormat) {
        Pageable pageable = PageRequest.of(page, size);
        List<StudentResponse> studentResponses = new ArrayList<>();
        PaginationResponse<StudentResponse> paginationResponse = new PaginationResponse<>();

        if (studyFormat.equals(StudyFormat.ALL)) {
            for (StudentEntity student : studentRepository.findAll(pageable)) {
                studentResponses.add(studentViewMapper.convertToStudentResponse(student));
            }
        } else {
            for (StudentEntity student : studentRepository.findStudentEntitiesByStudyFormat(pageable, studyFormat)) {
                studentResponses.add(studentViewMapper.convertToStudentResponse(student));
            }
        }
        paginationResponse.setResponseList(studentResponses);
        paginationResponse.setCurrentPage(pageable.getPageNumber() + 1);
        paginationResponse.setTotalPage(studentRepository.findAll(pageable).getTotalPages());
        return paginationResponse;
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
