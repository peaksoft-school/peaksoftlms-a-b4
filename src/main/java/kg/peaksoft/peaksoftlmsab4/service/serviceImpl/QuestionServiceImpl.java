package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.QuestionRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.QuestionResponse;
import kg.peaksoft.peaksoftlmsab4.Validation.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.QuestionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.QuestionMapper;
import kg.peaksoft.peaksoftlmsab4.repository.QuestionRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TestRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TestStudentRepository;
import kg.peaksoft.peaksoftlmsab4.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionServiceImpl implements QuestionService {


    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final TestRepository testRepository;
    private final TestStudentRepository testStudentRepository;

    @Override
    public QuestionResponse create(Long id, QuestionRequest questionRequest) {
        return questionMapper.viewQuestion(questionRepository.save(questionMapper.create(questionRequest)));
    }

    @Override
    public QuestionResponse update(Long id, QuestionRequest questionRequest) {
        QuestionEntity question = questionRepository.findById(id).orElseThrow(() -> {
            log.error("Question with id = {} does not exists", id);
            throw new NotFoundException(
                    String.format("Question with id = %s does not exists", id)
            );
        });
        questionMapper.update(question, questionRequest);
        return questionMapper.viewQuestion(questionRepository.save(question));
    }

    @Override
    public QuestionResponse findById(Long id) {
        return questionMapper.viewQuestion(questionRepository.findById(id).orElseThrow(() -> {
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
        return questionMapper.viewQuestion(question);
    }

    @Override
    public List<QuestionResponse> findAll() {
        return questionMapper.viewQuestions(questionRepository.findAll());
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

        return questionMapper.viewQuestion(questionRepository.save(question));
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
