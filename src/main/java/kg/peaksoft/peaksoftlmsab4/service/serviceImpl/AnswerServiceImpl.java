package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.AnswerRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.AnswerResponse;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.QuestionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestStudentEntity;
import kg.peaksoft.peaksoftlmsab4.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AnswerServiceImpl {
    private final QuestionRepository questionRepository;

    public AnswerResponse createAnswers(Long questionId, AnswerRequest answerRequest, AuthInfo authInfo) {

        int correctOption = 0;
        int userRightAnswer = 0;

        QuestionEntity question = questionRepository.findById(questionId).orElseThrow(() -> new NotFoundException(
                String.format("object 'question'")
        ));
        for (OptionEntity options : question.getOptions()) {
            if (options.getIsTrue()) {
                correctOption++;
            }
        }

        List<Long> uniqueList = answerRequest.getOptionId().stream().distinct().collect(Collectors.toList());

        for (int j = 0; j < question.getOptions().size(); j++) {
            for (Long aLong : uniqueList) {
                if (Objects.equals(aLong,
                        question.getOptions().get(j).getId())
                        && question.getOptions().get(j).getIsTrue()) {
                    userRightAnswer++;
                }
            }
        }

        int score = (userRightAnswer * 100 / correctOption) / 10;

        AnswerResponse answerResponse = new AnswerResponse();
        answerResponse.setQuestionId(questionId);
        answerResponse.setStudent(authInfo.getUsername());
        answerResponse.setResult(score);

        TestStudentEntity testStudentEntity = new TestStudentEntity();
        testStudentEntity.setResult(score);
        testStudentEntity.setStudent(authInfo.getUsername());
        testStudentEntity.setQuestionEntity(question);
        testStudentEntity.setLocalDate(LocalDate.now());
        question.setTestStudentEntity(testStudentEntity);
        questionRepository.save(question);
//        if(question.getTestStudentEntity().getResult() != 0){
//            question.getTestStudentEntity().setResult(score);
//            questionRepository.save(question);
//        }else{
//            question.getTestStudentEntity().setResult(question.getTestStudentEntity().getResult() + score);
//            questionRepository.save(question);
//        }

        return answerResponse;
    }
}
