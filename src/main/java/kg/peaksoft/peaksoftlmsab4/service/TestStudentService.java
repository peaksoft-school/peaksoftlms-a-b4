package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.ResultResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestStudentRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestStudentResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TestStudentService {


    List<TestStudentResponse> createResult(TestStudentRequest testStudentRequest);



}
