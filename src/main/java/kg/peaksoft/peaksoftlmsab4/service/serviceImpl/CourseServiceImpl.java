package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.CourseRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.CourseResponse;
import kg.peaksoft.peaksoftlmsab4.exception.AlreadyExistsException;
import kg.peaksoft.peaksoftlmsab4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.ResponseEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.CourseEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.CourseViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsab4.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    @Override
    public ResponseEntity saveCourse(CourseRequest courseRequest) {
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

        return ResponseEntity.builder()
                .httpStatus(HttpStatus.CREATED)
                .message(String.format("Course with name = %s has successfully saved to database", savedCourse.getCourseName()))
                .build();
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
    public ResponseEntity deleteCourseById(Long courseId) {
        boolean exists = courseRepository.existsById(courseId);

        if (!exists) {
            log.error("Course with id = {} does not exists, you can't delete it", courseId);
            throw new BadRequestException(
                    String.format("Course with id = %s does not exists, you can't delete it", courseId)
            );
        }
        courseRepository.deleteById(courseId);

        log.info("Course with id = {} has successfully deleted", courseId);

        return ResponseEntity.builder()
                .httpStatus(HttpStatus.MOVED_PERMANENTLY)
                .message(String.format("Course with id = %s has successfully deleted", courseId))
                .build();
    }

    @Override
    public ResponseEntity updateCourseById(Long courseId, CourseRequest courseRequest) {
        CourseEntity course = getByIdMethod(courseId);
        courseMapper.update(course, courseRequest);
        courseRepository.save(course);
        return ResponseEntity.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Course with id = %d has successfully updated", courseId))
                .build();
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
