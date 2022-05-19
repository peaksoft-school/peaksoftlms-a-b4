package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.*;
import kg.peaksoft.peaksoftlmsab4.exception.AlreadyExistsException;
import kg.peaksoft.peaksoftlmsab4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.InstructorEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.CourseEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.CourseViewMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.InstructorViewMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.StudentViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsab4.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseEditMapper courseMapper;
    private final CourseViewMapper courseViewMapper;
    private final StudentViewMapper studentViewMapper;
    private final InstructorViewMapper instructorViewMapper;

    @Override
    public CourseResponse saveCourse(CourseRequest courseRequest) {
        boolean exists = courseRepository.existsByCourseName(courseRequest.getCourseName());
        if (exists) {
            log.info("Course with name = {} already exists", courseRequest.getCourseName());
            throw new AlreadyExistsException(
                    "Course with name = " + courseRequest.getCourseName() + " already exists"
            );
        }
        CourseEntity course = courseMapper.create(courseRequest);
        CourseEntity savedCourse = courseRepository.save(course);

        log.info("Course with name = {} has successfully saved to database", savedCourse.getCourseName());

        return courseViewMapper.viewCourse(savedCourse);
    }

    @Override
    public List<CourseResponse> findAllCourse() {
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (CourseEntity course : courseRepository.findAll()) {
            courseResponses.add(courseViewMapper.viewCourse(course));
        }
        log.info("Found {} courses", courseResponses.size());
        return courseResponses;
    }

    @Override
    public CourseResponse getById(Long courseId) {
        CourseEntity course = getByIdMethod(courseId);

        log.info("Founded course with id = {}", courseId);
        return courseViewMapper.viewCourse(course);
    }

    @Override
    public CourseResponse deleteCourseById(Long courseId) {
        boolean exists = courseRepository.existsById(courseId);

        if (!exists) {
            log.error("Course with id = {} does not exists, you can't delete it", courseId);
            throw new BadRequestException(
                    String.format("Course with id = %s does not exists, you can't delete it", courseId)
            );
        }
        CourseEntity deletedCourse = getByIdMethod(courseId);
        courseRepository.deleteById(courseId);

        log.info("Course with id = {} has successfully deleted", courseId);

        return courseViewMapper.viewCourse(deletedCourse);
    }

    @Override
    public CourseResponse updateCourseById(Long courseId, CourseRequest courseRequest) {
        CourseEntity course = getByIdMethod(courseId);
        courseMapper.update(course, courseRequest);
        courseRepository.save(course);
        return courseViewMapper.viewCourse(course);
    }

    @Override
    public List<StudentResponse> getAllStudentsByCourseId(Long id) {
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (StudentEntity s : getByIdMethod(id).getStudents()) {
            studentResponses.add(studentViewMapper.convertToStudentResponse(s));
        }
        log.info("Successfully getAll Students by Course Id");
        return studentResponses;
    }

    @Override
    public List<InstructorResponse> getAllTeacherByCourseId(Long id) {
        List<InstructorResponse> instructorResponses = new ArrayList<>();
        for (InstructorEntity i : getByIdMethod(id).getInstructors()) {
            instructorResponses.add(instructorViewMapper.convertToInstructorResponse(i));
        }
        log.info("successfully getAll teacher by Course Id");
        return instructorResponses;
    }

    @Override
    public PaginationResponse<CourseResponse> getCoursePagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (CourseEntity course:courseRepository.findAllPag(pageable)) {
            courseResponses.add(courseViewMapper.viewCourse(course));
        }
        PaginationResponse<CourseResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setResponseList(courseResponses);
        paginationResponse.setCurrentPage(pageable.getPageNumber()+1);
        paginationResponse.setTotalPage(courseRepository.findAll().size());
        return paginationResponse;
    }

    private CourseEntity getByIdMethod(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> {
                    log.error("Course with id = {} does not exists", courseId);
                    throw new NotFoundException(
                            String.format("Course with id = %s does not exists", courseId)
                    );
                });
    }
}
