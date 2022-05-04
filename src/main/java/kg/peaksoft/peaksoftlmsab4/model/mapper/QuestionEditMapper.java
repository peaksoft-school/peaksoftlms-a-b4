package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.QuestionRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.QuestionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QuestionEditMapper {

    public QuestionEntity create(QuestionRequest questionRequest) {
        if (questionRequest == null) {
            log.error("The request is null!");
            return null;
        }
        QuestionEntity question = new QuestionEntity();
        question.setQuestion(questionRequest.getQuestion());
        question.setQuestionType(questionRequest.getQuestionType());
        return question;
    }

    public QuestionEntity update(QuestionEntity question, QuestionRequest questionRequest) {
        question.setQuestion(questionRequest.getQuestion());
        return question;

    }
}
