package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.LessonRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.LessonResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.LessonResponseForGet;
import kg.peaksoft.peaksoftlmsab4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.LessonMapper;
import kg.peaksoft.peaksoftlmsab4.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsab4.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsab4.service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class LessonServiceImpl implements LessonService {

    private final LessonMapper mapper;
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Override
    public LessonResponse create(LessonRequest lessonRequest, Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> {
                    log.error("Course with id = {} does not exists", courseId);
                    throw new NotFoundException(
                            String.format("Course with id = %s does not exists", courseId)
                    );
                });
        LessonEntity lesson = mapper.mapToEntity(lessonRequest);
        lesson.setCourseEntity(courseEntity);
        log.info(" Lesson with name : {} has successfully saved to database", lesson.getLessonName());
        return mapper.mapToResponse(lessonRepository.save(lesson));
    }

    @Override
    public List<LessonResponseForGet> getAll() {
        log.info("Found {} lessons ", lessonRepository.findAll().size());
        return mapper.mapToResponseForGetMethod(lessonRepository.findAll());
    }

    @Override
    public LessonResponseForGet getById(Long lessonId) {
        LessonEntity lesson = lessonRepository.findById(lessonId).
                orElseThrow(() -> {
                    log.error("Lesson with id = {} does not exists", lessonId);
                    throw new NotFoundException(
                            String.format("Lesson with id = %s does not exists", lessonId)
                    );
                });
        return mapper.mapToResponseForGetMethod(lesson);
    }

    @Override
    public LessonResponse update(Long lessonId, LessonRequest lessonRequest) {
        LessonEntity lesson = lessonRepository.findById(lessonId).
                orElseThrow(() -> {
                    log.error("Lesson with id = {} does not exists", lessonId);
                    throw new NotFoundException(
                            String.format("Lesson with id = %s does not exists", lessonId)
                    );
                });
        mapper.update(lesson, lessonRequest);
        return mapper.mapToResponse(lessonRepository.save(lesson));
    }

    @Override
    public LessonResponse deleteById(Long id) {
        boolean existById = lessonRepository.existsById(id);
        if (!existById) {
            log.error("Lesson with id = {} does not exists, you can not delete it", id);
            throw new BadRequestException(
                    String.format("Lesson with id = %s does not exists, you can not delete it", id)
            );
        }
        LessonEntity lessonEntity=lessonRepository.findById(id).
                orElseThrow(() -> {
                    log.error("Lesson with id = {} does not exists", id);
                    throw new NotFoundException(
                            String.format("Lesson with id = %s does not exists", id)
                    );
                });
        lessonRepository.deleteById(id);
       return mapper.mapToResponse(lessonEntity);
    }
}
