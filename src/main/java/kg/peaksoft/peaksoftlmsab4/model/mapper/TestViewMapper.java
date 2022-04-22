package kg.peaksoft.peaksoftlmsab4.model.mapper;




import kg.peaksoft.peaksoftlmsab4.api.payload.TestResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestViewMapper {

    public TestResponse viewTest(TestEntity test) {
        if (test == null) {
            log.error("The group db is null!");
            return null;
        }
       TestResponse testResponse=new TestResponse();
        if (test.getId() != null) {
            testResponse.setId(test.getId());
        }
        testResponse.setTestName(test.getTestName());
        return testResponse;

    }
    public List<TestResponse> viewTests(List<TestEntity>tests){
        List<TestResponse> testResponses=new ArrayList<>();
        for (TestEntity t:tests) {
            testResponses.add(viewTest(t));
        }
        return testResponses;
    }
}