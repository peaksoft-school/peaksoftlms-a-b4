package kg.peaksoft.peaksoftlmsab4.api.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kg.peaksoft.peaksoftlmsab4.api.payload.ResultResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestStudentRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestStudentResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.service.TestStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/results")

public class TestStudentApi {

    private final TestStudentService service;


    @PostMapping("/save")
    public List<TestStudentResponse> createResult(@RequestBody TestStudentRequest testStudentRequest) {
//        AuthInfo principal = (AuthInfo) authentication.getPrincipal();
        return service.createResult(testStudentRequest);
    }

//    @PutMapping("/update/{id}")
//    public TestResultResponse update(@PathVariable Long id, @RequestBody TestResultRequest testResultRequest) {
//        return service.update(id, testResultRequest);
//    }

    @GetMapping("/get")
    public ResultResponse result() {
        return service.result();
    }

    @GetMapping("/gets")
    public ResultResponse testResults() {
        return service.testResults();
    }


}
