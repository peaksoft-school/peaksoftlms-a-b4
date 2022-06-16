package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.*;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestStudentEntity;
import kg.peaksoft.peaksoftlmsab4.repository.OptionRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TestRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TestStudentRepository;
import kg.peaksoft.peaksoftlmsab4.service.ResultService;
import kg.peaksoft.peaksoftlmsab4.service.serviceImpl.ResultServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor

public class TestStudentMapper {
//    private final OptionRepository optionRepository;
//    private final TestRepository testRepository;
//    private final ResultService resultService;
//
//
//    public List<TestStudentEntity> createResult(TestStudentRequest testStudentRequest) {
//
//
//        List<OptionEntity> options = optionRepository.findAll();
//        List<TestStudentEntity> resultEntities = new ArrayList<>();
//        TestStudentEntity testStudent = new TestStudentEntity();
//
//        for (OptionEntity o : options) {
//            testStudent.setAnswer(o.getOption());
//            testStudent.setIsTrue(o.getIsTrue());
//            resultEntities.add(testStudent);
//        }
//
//
//        List<OptionRequestForTest> optionsListAnswer = new ArrayList<>();
//        List<QuestionRequestForTest> questions = testStudentRequest.getQuestions();
//        for (QuestionRequestForTest questionRequest:questions) {
//            List<OptionRequestForTest> optionsList =  questionRequest.getOptions();
//            optionsListAnswer.addAll(optionsList);
//        }
//
//        for (OptionEntity optionEntity : options) {
//             Long id = optionEntity.getId();
//            for (OptionRequestForTest optionRequestForTest: optionsListAnswer) {
//                Long optionId = optionRequestForTest.getOptionId();
//                if (Objects.equals(id, optionId) && optionEntity.getIsTrue().equals(true)){
//                    ResultRequest resultRequest = new ResultRequest();
//                    resultRequest.setAccepted(true);
//                     resultService.createResult(resultRequest);
//                }else {
//                    ResultRequest resultRequest = new ResultRequest();
//                    resultRequest.setAccepted(false);
//                    resultService.createResult(resultRequest);
//                }
//            }
//        }
//
//        return resultEntities;
//    }
//
//
//    public TestStudentEntity update(TestStudentEntity testResult, TestStudentRequest testResultRequest) {
//
//        return testResult;
//    }
//
//    private OptionEntity getByIdMethod(Long optionId) {
//        return optionRepository.findById(optionId).
//                orElseThrow(() -> {
//                    log.error("Question with id = {} does not exists", optionId);
//                    throw new NotFoundException(
//                            String.format("Question with id = %s does not exists", optionId)
//                    );
//                });
//    }
//
//    public TestStudentResponse viewTestResult(TestStudentEntity resultEntity) {
//        TestStudentResponse testResultResponse = new TestStudentResponse();
//        testResultResponse.setId(resultEntity.getId());
//        testResultResponse.setOption(resultEntity.getAnswer());
//
//
//        return testResultResponse;
//    }
//
//    public List<TestStudentResponse> viewTestResults(List<TestStudentEntity> testStudentEntities) {
//
//        List<TestStudentResponse> testResultResponses = new ArrayList<>();
//        for (TestStudentEntity o : testStudentEntities) {
//            testResultResponses.add(viewTestResult(o));
//        }
//        return testResultResponses;
//    }
}
