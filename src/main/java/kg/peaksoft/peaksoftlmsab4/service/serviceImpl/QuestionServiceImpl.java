package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.QuestionRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.QuestionResponse;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.QuestionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.QuestionEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.QuestionViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.QuestionRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TestRepository;
import kg.peaksoft.peaksoftlmsab4.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    private final QuestionViewMapper viewMapper;
    private final QuestionRepository questionRepository;
    private final QuestionEditMapper editMapper;
    private final TestRepository testRepository;


    @Override
    public QuestionResponse create(QuestionRequest questionRequest) {

        return viewMapper.viewQuestion(questionRepository.save(editMapper.create(questionRequest)));
    }

    @Override
    public QuestionResponse update(Long id, QuestionRequest questionRequest) {
        QuestionEntity question = questionRepository.findById(id).orElseThrow(() -> {
            log.error("Question with id = {} does not exists", id);
            throw new NotFoundException(
                    String.format("Question with id = %s does not exists", id)
            );
        });
        editMapper.update(question, questionRequest);
        return viewMapper.viewQuestion(questionRepository.save(question));
    }

    @Override
    public QuestionResponse findById(Long id) {
        return viewMapper.viewQuestion(questionRepository.findById(id).orElseThrow(() -> {
            log.error("Question with id = {} does not exists", id);
            throw new NotFoundException(
                    String.format("Question with id = %s does not exists", id)
            );
        }));
    }

    @Override
    public QuestionResponse deleteById(Long id) {
        QuestionEntity question = getByIdMethod(id);
        questionRepository.deleteById(id);
        return viewMapper.viewQuestion(question);
    }

    @Override
    public List<QuestionResponse> findAll() {
        return viewMapper.viewQuestions(questionRepository.findAll());
    }

    @Override
    public QuestionResponse findByQuestionToTest(Long testId, Long questionId) {
        TestEntity test = testRepository.findById(testId)
                .orElseThrow(() -> {
                    log.error("Test with id = {} does not exists", testId);
                    throw new NotFoundException(
                            String.format("Test with id = %s does not exists", testId)
                    );
                });
        QuestionEntity question = getByIdMethod(questionId);
        question.setTestEntity(test);
        return viewMapper.viewQuestion(questionRepository.save(question));
    }

    private QuestionEntity getByIdMethod(Long questionId) {
        return questionRepository.findById(questionId).
                orElseThrow(() -> {
                    log.error("Question with id = {} does not exists", questionId);
                    throw new NotFoundException(
                            String.format("Question with id = %s does not exists", questionId)
                    );
                });
    }
}
