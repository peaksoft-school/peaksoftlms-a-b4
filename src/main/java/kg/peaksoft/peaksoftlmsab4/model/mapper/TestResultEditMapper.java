package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.TestResultRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestResultEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TestResultEditMapper {

    public TestResultEntity create(TestResultRequest testResultRequest) {
        TestResultEntity testResult = new TestResultEntity();
        OptionEntity option = new OptionEntity();

        if (testResultRequest.getOptionId()== option.getId()){
           testResult.setId(option.getId());
           testResult.setAnswer(option.getAnswer());
        }
            return testResult;

    }

    public TestResultEntity update(TestResultEntity testResult, TestResultRequest testResultRequest) {

        testResult.setId(testResultRequest.getOptionId());
        return testResult;
    }
}
