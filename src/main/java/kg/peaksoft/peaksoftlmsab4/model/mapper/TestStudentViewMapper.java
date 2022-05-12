package kg.peaksoft.peaksoftlmsab4.model.mapper;


import kg.peaksoft.peaksoftlmsab4.api.payload.TestStudentResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestStudentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TestStudentViewMapper {

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
