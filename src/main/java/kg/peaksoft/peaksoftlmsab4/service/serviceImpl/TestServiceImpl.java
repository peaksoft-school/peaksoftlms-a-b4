package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.TestRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.TestResponse;
import kg.peaksoft.peaksoftlmsab4.validation.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.validation.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.TestMapper;
import kg.peaksoft.peaksoftlmsab4.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TestRepository;
import kg.peaksoft.peaksoftlmsab4.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TestServiceImpl implements TestService {

    private final LessonRepository lessonRepository;
    private final TestRepository repository;
    private final TestMapper testMapper;

    @Override
    public TestResponse create(TestRequest request, Long lessonId) {
        LessonEntity lesson = lessonRepository.findById(lessonId).orElseThrow(() -> {
            log.error("Lesson with id = {} does not exists", lessonId);
            throw new NotFoundException(
                    String.format("Lesson with id = %s does not exists", lessonId)
            );
        });
        if (lesson.getTestEntity() == null) {
            TestEntity test = testMapper.create(request);
            test.setLessonEntity(lesson);
            return testMapper.viewTest(repository.save(test));
        } else {
            throw new BadRequestException("In this lesson test already exists");
        }
    }

    @Override
    public TestResponse update(Long id, TestRequest request) {
        TestEntity test = repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("test not found this=%s", id)));

        repository.deleteById(id);
        TestEntity newTest = testMapper.create(request);
        newTest.setLessonEntity(test.getLessonEntity());
        repository.save(newTest);
        return testMapper.viewTest(newTest);
    }

    @Override
    public TestResponse findById(Long id) {
        return testMapper.viewTest(repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("test not found this=%s", id)
        )));
    }

    @Override
    public TestResponse deleteById(Long id) {
        TestEntity test = repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("test not found this=%s", id)));
        repository.deleteById(id);
        return testMapper.viewTest(test);
    }

    @Override
    public List<TestResponse> findAll() {
        return testMapper.viewTests(repository.findAll());
    }
}
