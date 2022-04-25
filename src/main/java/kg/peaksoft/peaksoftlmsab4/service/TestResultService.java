package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.TestResultRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestResultResponse;

import java.util.List;

public interface TestResultService {
    TestResultResponse create(TestResultRequest testResultRequest);

    TestResultResponse update(Long id, TestResultRequest testResultRequest);

    TestResultResponse findById(Long id);

    TestResultResponse deleteById(Long id);

    List<TestResultResponse> findAll();
}
