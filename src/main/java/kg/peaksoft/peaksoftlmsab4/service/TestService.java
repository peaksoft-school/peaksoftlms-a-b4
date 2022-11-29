package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.TestRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.TestResponse;

import java.util.List;

public interface TestService {

    TestResponse create(TestRequest request, Long id);

    TestResponse update(Long id, TestRequest request);

    TestResponse findById(Long id);

    TestResponse deleteById(Long id);

    List<TestResponse> findAll();

}
