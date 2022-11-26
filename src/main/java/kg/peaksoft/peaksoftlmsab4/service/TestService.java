package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.TestRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.TestResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestService {

    TestResponse create(TestRequest testRequest,Long id);

    TestResponse update(Long id,TestRequest testRequest);

    TestResponse findById(Long id);

    TestResponse deleteById(Long id);

    List<TestResponse> findAll();

}
