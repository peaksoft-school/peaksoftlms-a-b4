package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.TestRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestService {

    TestResponse create(TestRequest testRequest);

    TestResponse update(Long id,TestRequest testRequest);

    TestResponse findById(Long id);

    TestResponse deleteById(Long id);

    List<TestResponse> findAll();

}
