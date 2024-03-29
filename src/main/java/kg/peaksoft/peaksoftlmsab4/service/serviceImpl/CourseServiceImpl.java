package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.AssignRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.request.CourseRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.*;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.InstructorEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.*;
import kg.peaksoft.peaksoftlmsab4.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsab4.repository.InstructorRepository;
import kg.peaksoft.peaksoftlmsab4.service.CourseService;
import kg.peaksoft.peaksoftlmsab4.validation.exception.AlreadyExistsException;
import kg.peaksoft.peaksoftlmsab4.validation.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.validation.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseEditMapper courseMapper;
    private final CourseViewMapper courseViewMapper;
    private final StudentViewMapper studentViewMapper;
    private final InstructorViewMapper instructorViewMapper;
    private final InstructorRepository instructorRepository;
    private final LessonMapper lessonMapper;

    @Override
    public CourseResponse saveCourse(CourseRequest request) {
        checkByName(request.getCourseName());
        CourseEntity course = courseMapper.create(request);
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
                    String.format("Course with id = %s does not exists, you can't delete it", courseId));
        }
        CourseEntity deletedCourse = getByIdMethod(courseId);
        courseRepository.deleteById(courseId);
        log.info("Course with id = {} has successfully deleted", courseId);
        return courseViewMapper.viewCourse(deletedCourse);
    }

    @Override
    public CourseResponse updateCourseById(Long courseId, CourseRequest request) {
        CourseEntity course = getByIdMethod(courseId);
        String courseName = request.getCourseName();
        String courseEntityName = course.getCourseName();
        if (!courseName.equals(courseEntityName)) {
            checkByName(courseName);
        }
        courseMapper.update(course, request);
        courseRepository.save(course);
        return courseViewMapper.viewCourse(course);
    }

    @Override
    public List<StudentResponse> getAllStudentsByCourseId(Long id) {
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (StudentEntity s : courseRepository.getStudents(id)) {
            studentResponses.add(studentViewMapper.convertToStudentResponse(s));
        }
        log.info("Successfully getAll Students by Course Id");
        return studentResponses;
    }

    @Override
    public List<InstructorResponse> getAllInstructorByCourseId(Long id) {
        List<InstructorResponse> instructorResponses = new ArrayList<>();
        for (InstructorEntity i : getByIdMethod(id).getInstructors()) {
            instructorResponses.add(instructorViewMapper.convertToInstructorResponse(i));
        }
        log.info("successfully getAll teacher by Course Id");
        return instructorResponses;
    }

    @Override
    public List<LessonResponseForGet> getAllLessonByCourseId(Long id) {
        List<LessonResponseForGet> lessonResponses = new ArrayList<>();
        for (LessonEntity l : getByIdMethod(id).getLessons()) {
            lessonResponses.add(lessonMapper.mapToResponseForGetMethod(l));
        }
        log.info("successfully getAll lesson by Course Id");
        return lessonResponses;
    }

    @Override
    @Transactional
    public String assignInstructorToCourse(AssignRequest request) {
        CourseEntity course = courseRepository.findById(request.getCourseId()).orElseThrow(() ->
                new NotFoundException("Course not found by this id"));
        for (Long id : request.getInstructorsId()) {
            InstructorEntity instructor = instructorRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Instructor not found by this id"));
            for (InstructorEntity instructorEntity : course.getInstructors()) {
                if (instructor.getId().equals(instructorEntity.getId())) {
                    throw new AlreadyExistsException(
                            "Instructors with id: " + instructor.getId() +
                                    " already assigned to course " + course.getCourseName());
                }
            }
            course.setInstructor(instructor);
        }
        courseRepository.save(course);
        return String.format("All instructors added to course = %s", course.getCourseName());
    }

    @Override
    public PaginationResponse<CourseResponse> getCoursePagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (CourseEntity course : courseRepository.findAllPag(pageable)) {
            courseResponses.add(courseViewMapper.viewCourse(course));
        }
        PaginationResponse<CourseResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setResponseList(courseResponses);
        paginationResponse.setCurrentPage(pageable.getPageNumber() + 1);
        paginationResponse.setTotalPage(courseRepository.findAll(pageable).getTotalPages());
        return paginationResponse;
    }

    private CourseEntity getByIdMethod(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> {
            log.error("Course with id = {} does not exists", courseId);
            throw new NotFoundException(
                    String.format("Course with id = %s does not exists", courseId));
        });
    }

    private void checkByName(String name) {
        boolean exists = courseRepository.existsByCourseName(name);
        if (exists) {
            log.info("Course with name = {} already exists", name);
            throw new AlreadyExistsException("Course with name = " + name + " already exists");
        }
    }

}
