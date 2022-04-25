package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.QuestionRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.QuestionResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.QuestionEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.QuestionEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.QuestionViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.QuestionRepository;
import kg.peaksoft.peaksoftlmsab4.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionViewMapper viewMapper;
    private final QuestionRepository questionRepository;
    private final QuestionEditMapper editMapper;

    @Override
    public QuestionResponse create(QuestionRequest questionRequest) {
        return viewMapper.viewQuestion(questionRepository.save(editMapper.create(questionRequest)));
    }

    @Override
    public QuestionResponse update(Long id, QuestionRequest questionRequest) {
        QuestionEntity question=questionRepository.findById(id).get();
        editMapper.update(question,questionRequest);
        return viewMapper.viewQuestion(questionRepository.save(question));
    }

    @Override
    public QuestionResponse findById(Long id) {
        return viewMapper.viewQuestion(questionRepository.findById(id).get());
    }

    @Override
    public QuestionResponse deleteById(Long id) {
        QuestionEntity question=questionRepository.findById(id).get();
        questionRepository.deleteById(id);
        return viewMapper.viewQuestion(question);
    }

    @Override
    public List<QuestionResponse> findAll() {
        return viewMapper.viewQuestions(questionRepository.findAll());
    }
}
