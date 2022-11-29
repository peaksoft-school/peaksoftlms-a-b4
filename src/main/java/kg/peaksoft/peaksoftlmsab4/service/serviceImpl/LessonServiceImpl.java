package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.LessonRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.LessonResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.LessonResponseForGet;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.LessonMapper;
import kg.peaksoft.peaksoftlmsab4.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsab4.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsab4.service.LessonService;
import kg.peaksoft.peaksoftlmsab4.validation.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.validation.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonMapper mapper;
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Override
    public LessonResponse create(LessonRequest request, Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId).orElseThrow(() -> {
            log.error("Course with id = {} does not exists", courseId);
            throw new NotFoundException(
                    String.format("Course with id = %s does not exists", courseId));
        });
        LessonEntity lesson = mapper.mapToEntity(request);
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
        LessonEntity lesson = lessonRepository.findById(lessonId).orElseThrow(() -> {
            log.error("Lesson with id = {} does not exists", lessonId);
            throw new NotFoundException(
                    String.format("Lesson with id = %s does not exists", lessonId));
        });
        return mapper.mapToResponseForGetMethod(lesson);
    }

    @Override
    public LessonResponse update(Long lessonId, LessonRequest request) {
        LessonEntity lesson = lessonRepository.findById(lessonId).orElseThrow(() -> {
            log.error("Lesson with id = {} does not exists", lessonId);
            throw new NotFoundException(String.format("Lesson with id = %s does not exists", lessonId));
        });
        mapper.update(lesson, request);
        return mapper.mapToResponse(lessonRepository.save(lesson));
    }

    @Override
    public LessonResponse deleteById(Long id) {
        boolean existById = lessonRepository.existsById(id);
        if (!existById) {
            log.error("Lesson with id = {} does not exists, you can not delete it", id);
            throw new BadRequestException(String.format("Lesson with id = %s does not exists, you can not delete it", id));
        }
        LessonEntity lessonEntity = lessonRepository.findById(id).orElseThrow(() -> {
            log.error("Lesson with id = {} does not exists", id);
            throw new NotFoundException(String.format("Lesson with id = %s does not exists", id));
        });
        lessonRepository.deleteById(id);
        return mapper.mapToResponse(lessonEntity);
    }

}
