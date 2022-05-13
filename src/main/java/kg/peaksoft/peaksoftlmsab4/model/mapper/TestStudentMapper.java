package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.TestStudentRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestStudentResponse;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestStudentEntity;
import kg.peaksoft.peaksoftlmsab4.repository.OptionRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TestStudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor

public class TestStudentMapper {
    private final OptionRepository optionRepository;



    public List<TestStudentEntity> createResult(TestStudentRequest testStudentRequest) {


        List<OptionEntity> options = optionRepository.findAll();

        List<TestStudentEntity> resultEntities = new ArrayList<>();

        TestStudentEntity testStudent = new TestStudentEntity();

        for (OptionEntity o : options) {
            testStudent.setAnswer(o.getAnswer());
            testStudent.setIsTrue(o.getIsTrue());
            resultEntities.add(testStudent);
        }
        return resultEntities;
    }


    public TestStudentEntity update(TestStudentEntity testResult, TestStudentRequest testResultRequest) {

        return testResult;
    }

    private OptionEntity getByIdMethod(Long optionId) {
        return optionRepository.findById(optionId).
                orElseThrow(() -> {
                    log.error("Question with id = {} does not exists", optionId);
                    throw new NotFoundException(
                            String.format("Question with id = %s does not exists", optionId)
                    );
                });
    }

    public TestStudentResponse viewTestResult(TestStudentEntity resultEntity) {
        TestStudentResponse testResultResponse = new TestStudentResponse();
        testResultResponse.setId(resultEntity.getId());
        testResultResponse.setAnswer(resultEntity.getAnswer());


        return testResultResponse;
    }

    public List<TestStudentResponse> viewTestResults(List<TestStudentEntity> testStudentEntities) {

        List<TestStudentResponse> testResultResponses = new ArrayList<>();
        for (TestStudentEntity o : testStudentEntities) {
            testResultResponses.add(viewTestResult(o));
        }
        return testResultResponses;
    }
}
