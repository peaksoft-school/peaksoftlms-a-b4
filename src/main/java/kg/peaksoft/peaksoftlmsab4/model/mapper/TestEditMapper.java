package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.TestRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestEditMapper {

    public TestEntity create(TestRequest testRequest) {
        if (testRequest == null) {
            log.error("The request is null!");
            return null;
        }
        TestEntity test = new TestEntity();
        test.setTestName(testRequest.getTestName());
        return test;
    }

    public TestEntity update(TestEntity test, TestRequest testRequest) {

        test.setTestName(testRequest.getTestName());
        return test;

    }
}
