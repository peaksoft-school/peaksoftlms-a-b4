package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.AnswerRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.AnswerResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.QuestionRequestForTest;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.*;
import kg.peaksoft.peaksoftlmsab4.repository.QuestionRepository;
import kg.peaksoft.peaksoftlmsab4.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TestRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TestStudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class AnswerServiceImpl {

    private final TestStudentRepository testStudentRepository;
    private final TestRepository testRepository;
    private final StudentRepository studentRepository;

    List<Integer> testResult;
    List<Long> optionsList;

    public void createAnswers(AnswerRequest answerRequest,AuthInfo authInfo) {

        int correctOption = 0;
        int userRightAnswer = 0;

        List<QuestionEntity> questionEntity = testRepository.allQuestionsFromTest(answerRequest.getTestId());
        for (QuestionEntity question:questionEntity) {
            for (OptionEntity options : question.getOptions()) {
                if (options.getIsTrue()) {
                    correctOption++;
                }
            }
        }

        List<QuestionRequestForTest> answerRequestQuestion = answerRequest.getQuestion();
        for (QuestionRequestForTest questionRequestForTest:answerRequestQuestion) {
            optionsList = questionRequestForTest.getOptions();
        }

            for (QuestionEntity question : questionEntity) {
                for (int j = 0; j < question.getOptions().size(); j++) {
                    for (Long aLong : optionsList) {
                        if (Objects.equals(aLong,
                                question.getOptions().get(j).getId())
                                && question.getOptions().get(j).getIsTrue()) {
                            userRightAnswer++;
                        }
                    }
                }
            }
        int score = (userRightAnswer * 100 / correctOption) / 10;
        if (testResult == null){
            testResult = new ArrayList<>();
        }
            testResult.add(score);

        int result = 0;

        for (int i = 0; i < testResult.size(); i++) {
            int a = testResult.get(i);
            result = result + a;
        }

        TestStudentEntity testStudentEntity = new TestStudentEntity();
        testStudentEntity.setResult(result);
        testStudentEntity.setStudentEntity(studentRepository.getByEmail(authInfo.getEmail()));
        testStudentEntity.setLocalDate(LocalDate.now());

        testStudentRepository.save(testStudentEntity);
    }

    public TestStudentEntity resultTest(AuthInfo authInfo) {
        return testStudentRepository.getByEmail(authInfo.getEmail());
    }
}
