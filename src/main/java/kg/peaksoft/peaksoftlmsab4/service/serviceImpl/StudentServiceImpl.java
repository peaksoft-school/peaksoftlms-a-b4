package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.PaginationResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.StudentRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.GroupEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
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



    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        String email = studentRequest.getEmail();
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
        studentEditMapper.updateStudent(student, studentRequest);
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
        student.setCourse(course);
        log.info("Student with id = {} has successfully added to course with id = {}", studentId, courseId);
        return studentViewMapper.convertToStudentResponse(studentRepository.save(student));
    }

    @Override
    public StudentResponse saveStudentWithGroup( StudentRequest studentRequest) {
        GroupEntity group = groupRepository.findById(studentRequest.getGroupId())
                .orElseThrow(() -> {
                    log.error("Group with id = {} does not exists", studentRequest.getGroupId());
                    throw new NotFoundException(
                            String.format("Group with id = %s does not exists", studentRequest.getGroupId())
                    );
                });
        System.out.println(group);
        String email = studentRequest.getEmail();
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

        for (int index = 0; index<wordSheet.getPhysicalNumberOfRows(); index++){
            if (index>0){
                StudentEntity student = new StudentEntity();
                XSSFRow row = wordSheet.getRow(index);
                student.setFirstName(row.getCell(0).getStringCellValue());
                student.setLastName(row.getCell(1).getStringCellValue());
                student.setPhoneNumber(String.valueOf((int)row.getCell(2).getNumericCellValue()));
                student.setStudyFormat(StudyFormat.valueOf(row.getCell(3).getStringCellValue()));

                AuthInfo authInfo = new AuthInfo();
                authInfo.setEmail(row.getCell(4).getStringCellValue());
                authInfo.setRole(Role.valueOf(row.getCell(5).getStringCellValue()));
                authInfo.setPassword(passwordEncoder.encode(row.getCell(6).getStringCellValue()));

                String email = authInfo.getEmail();
                checkEmail(email);

                student.setAuthInfo(authInfo);
                students.add(student);
            }
        }

        for (StudentEntity student: students){
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
    public PaginationResponse<StudentResponse> getStudentPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (StudentEntity student:studentRepository.findAllPag(pageable)) {
            studentResponses.add(studentViewMapper.convertToStudentResponse(student));
        }
        PaginationResponse<StudentResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setResponseList(studentResponses);
        paginationResponse.setCurrentPage(pageable.getPageNumber()+1);
        paginationResponse.setPageSize(pageable.getPageSize());
        paginationResponse.setListSize(studentRepository.findAll().size());
        paginationResponse.setHowManyPages(
                paginationResponse.getListSize()%pageable.getPageSize()+
                paginationResponse.getListSize()/pageable.getPageSize());
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
