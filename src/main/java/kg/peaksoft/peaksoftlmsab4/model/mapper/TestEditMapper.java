package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.TestRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestEditMapper {

    public TestEntity create(TestRequest testRequest){
        if (testRequest==null){
            return null;
        }
        TestEntity test=new TestEntity();
        test.setTestName(testRequest.getTestName());
        return test;
    }

    public  TestEntity update(TestEntity test,TestRequest testRequest){
        test.setTestName(testRequest.getTestName());
        return test;
    }
}
