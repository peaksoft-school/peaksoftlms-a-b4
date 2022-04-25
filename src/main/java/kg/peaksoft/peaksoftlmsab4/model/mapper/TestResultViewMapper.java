package kg.peaksoft.peaksoftlmsab4.model.mapper;


import kg.peaksoft.peaksoftlmsab4.api.payload.TestResultResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TestResultViewMapper {

    public TestResultResponse viewTestResult(TestResultEntity testResult) {
        if (testResult == null) {
            log.error("The result db is null!");
            return null;
        }
        TestResultResponse testResultResponse = new TestResultResponse();
        if (testResult.getId() != null) {
            testResultResponse.setId(testResult.getId());
        }
        testResultResponse.setCorrectAnswer(testResult.getCorrectAnswer());
        testResultResponse.setWrongAnswer(testResult.getWrongAnswer());
        testResultResponse.setPoint(testResult.getPoint());

        return testResultResponse;
    }

    public List<TestResultResponse>vewTestResults(List<TestResultEntity>resultEntities){
        List<TestResultResponse>testResultResponses=new ArrayList<>();
        for (TestResultEntity tre:resultEntities) {
            testResultResponses.add(viewTestResult(tre));
        }
        return testResultResponses;

    }

}
