package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.OptionRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.QuestionRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.QuestionEntity;
import kg.peaksoft.peaksoftlmsab4.service.OptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class QuestionEditMapper {
    private final OptionEditMapper optionEditMapper;
    public QuestionEntity create(QuestionRequest questionRequest) {
         List<OptionEntity>optionEntities=new ArrayList<>();
        QuestionEntity question = new QuestionEntity();
        question.setQuestion(questionRequest.getQuestion());
        question.setQuestionType(questionRequest.getQuestionType());
        for (OptionRequest o: questionRequest.getOptions()) {
            optionEntities.add(optionEditMapper.create(o));
        }
        question.setOptions(optionEntities);

        return question;
    }

    public QuestionEntity update(QuestionEntity question, QuestionRequest questionRequest) {
        question.setQuestion(questionRequest.getQuestion());
        return question;

    }
}
