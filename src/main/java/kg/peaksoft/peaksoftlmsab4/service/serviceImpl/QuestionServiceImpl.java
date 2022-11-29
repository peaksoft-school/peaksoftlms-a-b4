package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.QuestionRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.QuestionResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.QuestionEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.QuestionMapper;
import kg.peaksoft.peaksoftlmsab4.repository.QuestionRepository;
import kg.peaksoft.peaksoftlmsab4.service.QuestionService;
import kg.peaksoft.peaksoftlmsab4.validation.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Override
    public QuestionResponse create(Long id, QuestionRequest request) {
        return questionMapper.viewQuestion(questionRepository.save(questionMapper.create(request)));
    }

    @Override
    public QuestionResponse update(Long id, QuestionRequest request) {
        QuestionEntity question = questionRepository.findById(id).orElseThrow(() -> {
            log.error("Question with id = {} does not exists", id);
            throw new NotFoundException(String.format("Question with id = %s does not exists", id));
        });
        questionMapper.update(question, request);
        return questionMapper.viewQuestion(questionRepository.save(question));
    }

    @Override
    public QuestionResponse findById(Long id) {
        return questionMapper.viewQuestion(questionRepository.findById(id).orElseThrow(() -> {
            log.error("Question with id = {} does not exists", id);
            throw new NotFoundException(String.format("Question with id = %s does not exists", id));
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
        QuestionEntity question = getByIdMethod(questionId);
        return questionMapper.viewQuestion(questionRepository.save(question));
    }

    private QuestionEntity getByIdMethod(Long questionId) {
        return questionRepository.findById(questionId).orElseThrow(() -> {
            log.error("Question with id = {} does not exists", questionId);
            throw new NotFoundException(String.format("Question with id = %s does not exists", questionId));
        });
    }

}
