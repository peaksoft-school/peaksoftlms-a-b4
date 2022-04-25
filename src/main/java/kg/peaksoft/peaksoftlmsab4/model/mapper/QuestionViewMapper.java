package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.QuestionResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.QuestionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class QuestionViewMapper {

    public QuestionResponse viewQuestion(QuestionEntity question) {
        if (question == null) {
            log.error("The question db is null!");
            return null;
        }
        QuestionResponse questionResponse = new QuestionResponse();
        if (question.getId() != null) {
            questionResponse.setId(question.getId());
        }
        questionResponse.setQuestion(question.getQuestion());
        questionResponse.setOptions(question.getOptions());
        return questionResponse;
    }

    public List<QuestionResponse> viewQuestions(List<QuestionEntity> questions) {

        List<QuestionResponse> questionResponses = new ArrayList<>();
        for (QuestionEntity q : questions) {
            questionResponses.add(viewQuestion(q));
        }
        return questionResponses;
    }
}
