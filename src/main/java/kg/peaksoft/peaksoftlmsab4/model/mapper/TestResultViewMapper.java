package kg.peaksoft.peaksoftlmsab4.model.mapper;


import kg.peaksoft.peaksoftlmsab4.api.payload.TestResultResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TestResultViewMapper {

    public TestResultResponse viewTestResult(TestResultEntity resultEntity) {

        TestResultResponse testResultResponse = new TestResultResponse();
        if (resultEntity.getId() != null) {
            testResultResponse.setId(resultEntity.getId());
        }
        testResultResponse.setAnswer(resultEntity.getAnswer());


        return testResultResponse;
    }

//    public List<TestResultResponse> viewTestResults(List<TestResultEntity> testResultEntities) {
//
//        List<TestResultResponse> testResultResponses = new ArrayList<>();
//        for (TestResultEntity o : testResultEntities) {
//            testResultResponses.add(viewTestResult(o));
//        }
//        return testResultResponses;
//    }
//

}
