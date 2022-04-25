package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.TestResultRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestResultEntity;
import org.springframework.stereotype.Component;

@Component
public class TestResultEditMapper {

    public TestResultEntity create(TestResultRequest testResultRequest) {
        if (testResultRequest == null) {
            return null;
        }
        TestResultEntity testResult = new TestResultEntity();
        testResult.setCorrectAnswer(testResultRequest.getCorrectAnswer());
        testResult.setWrongAnswer(testResultRequest.getWrongAnswer());
        testResult.setPoint(testResultRequest.getPoint());

        return testResult;
    }

    public TestResultEntity update(TestResultEntity testResult,TestResultRequest testResultRequest){

        testResult.setCorrectAnswer(testResultRequest.getCorrectAnswer());
        testResult.setWrongAnswer(testResultRequest.getWrongAnswer());
        testResult.setPoint(testResultRequest.getPoint());
        return testResult;
    }
}
