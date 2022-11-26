package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.OptionRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.request.QuestionRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.QuestionResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.QuestionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class QuestionMapper {
    private final OptionMapper optionMapper;

    public QuestionEntity create(QuestionRequest questionRequest) {
         List<OptionEntity>optionEntities=new ArrayList<>();
        QuestionEntity question = new QuestionEntity();
        question.setQuestion(questionRequest.getQuestion());
        question.setQuestionType(questionRequest.getQuestionType());
        for (OptionRequest o: questionRequest.getOptions()) {
            optionEntities.add(optionMapper.create(o));
        }
        question.setOptions(optionEntities);

        return question;
    }

    public QuestionEntity update(QuestionEntity question, QuestionRequest questionRequest) {
        question.setQuestion(questionRequest.getQuestion());
        return question;

    }

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
        questionResponse.setQuestionType(question.getQuestionType());
        questionResponse.setOptions(optionMapper.viewOptions(question.getOptions()));
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
