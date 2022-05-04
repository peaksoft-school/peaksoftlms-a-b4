package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.ResultResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestResultRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestResultResponse;

public interface TestResultService {


    TestResultResponse create(TestResultRequest testResultRequest);


   // TestResultResponse update(Long id, TestResultRequest testResultRequest);


    ResultResponse result();


}
