package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.AnswerRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.AnswerResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.QuestionRequestForTest;
import kg.peaksoft.peaksoftlmsab4.api.payload.ResultResponse;
import kg.peaksoft.peaksoftlmsab4.exception.AlreadyExistsException;
import kg.peaksoft.peaksoftlmsab4.model.entity.*;
import kg.peaksoft.peaksoftlmsab4.model.enums.QuestionType;
import kg.peaksoft.peaksoftlmsab4.model.enums.TestResult;
import kg.peaksoft.peaksoftlmsab4.model.mapper.AnswerMapper;
import kg.peaksoft.peaksoftlmsab4.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AnswerServiceImpl {

    private final TestStudentRepository testStudentRepository;
    private final TestRepository testRepository;
    private final StudentRepository studentRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final AnswerMapper answerMapper;

    List<Integer> testResult;

    public void createAnswers(AnswerRequest answerRequest, AuthInfo authInfo) {

        int correctOption = 0;
        int userRightAnswer = 0;
        int countRightAnswers = 0;

        List<QuestionEntity> questionEntity = testRepository.allQuestionsFromTest(answerRequest.getTestId());
        for (QuestionEntity question : questionEntity) {
            for (OptionEntity options : question.getOptions()) {
                if (options.getIsTrue()) {
                    correctOption++;
                }
            }
        }
        List<QuestionRequestForTest> answerRequestQuestion = answerRequest.getQuestion();
        for (QuestionRequestForTest requestForTest : answerRequestQuestion) {

            int count = 0;
            List<Long> uniqueList = requestForTest.getOptions().stream().distinct().collect(Collectors.toList());
            QuestionEntity question = questionRepository.getById(requestForTest.getQuestionId());
            List<OptionEntity> optionEntityList = optionRepository.getOptions(question.getId());
            for (OptionEntity option : optionEntityList) {
                if (option.getIsTrue()) {
                    count++;
                }
            }

            System.out.println(count);
            for (int i = 0; i < count; i++) {
                System.out.println(question.getOptions().size() + "======");
                for (Long aLong : uniqueList) {
                    if (Objects.equals(aLong,
                            question.getOptions().get(i).getId())
                            && question.getOptions().get(i).getIsTrue()) {
                        userRightAnswer = userRightAnswer + (10 / count);
                        countRightAnswers++;
                    }
                }
            }
        }
        TestStudentEntity testStudentEntity = new TestStudentEntity();
        testStudentEntity.setResult(userRightAnswer);
        int result = countRightAnswers * 100 / correctOption;
        if (result > 50) {
            testStudentEntity.setTestResult(TestResult.PASSED);
        } else {
            testStudentEntity.setTestResult(TestResult.FAILED);
        }
        testStudentEntity.setStudentEntity(studentRepository.getByEmail(authInfo.getEmail()));
        testStudentEntity.setLocalDate(LocalDate.now());
        testStudentEntity.setTestEntity(testRepository.getById(answerRequest.getTestId()));

//            if () {
//                throw new AlreadyExistsException(
//                        String.format("student with email = %s already passed this test", authInfo.getEmail())
//                );
//            } else {
//            }
        testStudentRepository.save(testStudentEntity);
//        return answerMapper.viewAnswer(testStudentEntity);
    }

    public List<AnswerResponse> resultTest() {
       return answerMapper.mapToResponse(testStudentRepository.findAll());
    }
}
