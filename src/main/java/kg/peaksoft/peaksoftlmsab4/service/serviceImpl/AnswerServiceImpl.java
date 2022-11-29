package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.AnswerRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.request.QuestionRequestForTest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.AnswerResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.QuestionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestStudentEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.TestResult;
import kg.peaksoft.peaksoftlmsab4.model.mapper.AnswerMapper;
import kg.peaksoft.peaksoftlmsab4.repository.OptionRepository;
import kg.peaksoft.peaksoftlmsab4.repository.QuestionRepository;
import kg.peaksoft.peaksoftlmsab4.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TestRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TestStudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AnswerServiceImpl {

    private final TestStudentRepository testStudentRepository;
    private final TestRepository testRepository;
    private final StudentRepository studentRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final AnswerMapper answerMapper;

    List<Integer> testResult;

    public void createAnswers(AnswerRequest request, AuthInfo authInfo) {
        int correctOption = 0;
        int userRightAnswer = 0;
        int countRightAnswers = 0;

        List<QuestionEntity> questionEntity = testRepository.allQuestionsFromTest(request.getTestId());
        for (QuestionEntity question : questionEntity) {
            for (OptionEntity options : question.getOptions()) {
                if (options.getIsTrue()) {
                    correctOption++;
                }
            }
        }
        List<QuestionRequestForTest> answerRequestQuestion = request.getQuestion();
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
        testStudentEntity.setTestEntity(testRepository.getById(request.getTestId()));
        testStudentRepository.save(testStudentEntity);
    }

    public List<AnswerResponse> resultTest() {
        return answerMapper.mapToResponse(testStudentRepository.findAll());
    }

}
