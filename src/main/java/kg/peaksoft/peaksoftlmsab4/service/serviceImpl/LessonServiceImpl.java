package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.LessonRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.LessonResponse;
import kg.peaksoft.peaksoftlmsab4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.LessonEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.LessonViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsab4.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsab4.service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class LessonServiceImpl implements LessonService {

    private final LessonEditMapper lessonEditMapper;
    private final LessonViewMapper lessonViewMapper;
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Override
    public LessonResponse addLesson(LessonRequest lessonRequest, Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> {
                    log.error("Course with id = {} does not exists", courseId);
                    throw new NotFoundException(
                            String.format("Course with id = %s does not exists", courseId)
                    );
                });

        LessonEntity lesson = lessonRepository.save(lessonEditMapper.convertToLesson(lessonRequest));
        courseEntity.setLesson(lesson);
        log.info(" Lesson with name : {} has successfully saved to database", lesson.getLessonName());
        return lessonViewMapper.convertToLessonResponse(lesson);
    }

    @Override
    public List<LessonResponse> getAllStudent() {
        List<LessonResponse> lessonResponses = new ArrayList<>();
        for (LessonEntity lesson : lessonRepository.findAll()) {
            lessonResponses.add(lessonViewMapper.convertToLessonResponse(lesson));
        }
        log.info("Found {} instructors ", lessonResponses.size());
        return lessonResponses;
    }

    @Override
    public LessonResponse getStudentById(Long lessonId) {
        LessonEntity lesson = lessonRepository.findById(lessonId).
                orElseThrow(() -> {
                    log.error("Lesson with id = {} does not exists", lessonId);
                    throw new NotFoundException(
                            String.format("Lesson with id = %s does not exists", lessonId)
                    );
                });
        return lessonViewMapper.convertToLessonResponse(lesson);
    }

    @Override
    public LessonResponse updateStudent(Long lessonId, LessonRequest lessonRequest) {
        LessonEntity lesson = lessonRepository.findById(lessonId).
                orElseThrow(() -> {
                    log.error("Lesson with id = {} does not exists", lessonId);
                    throw new NotFoundException(
                            String.format("Lesson with id = %s does not exists", lessonId)
                    );
                });
        lessonEditMapper.updateStudent(lesson, lessonRequest);
        return lessonViewMapper.convertToLessonResponse(lessonRepository.save(lesson));
    }

    @Override
    public void deleteStudent(Long id) {
        boolean existById = lessonRepository.existsById(id);
        if (!existById) {
            log.error("Lesson with id = {} does not exists, you can not delete it", id);
            throw new BadRequestException(
                    String.format("Lesson with id = %s does not exists, you can not delete it", id)
            );
        }
        lessonRepository.deleteById(id);
    }
}
