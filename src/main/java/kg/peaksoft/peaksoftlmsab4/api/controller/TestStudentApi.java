package kg.peaksoft.peaksoftlmsab4.api.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestStudentRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestStudentResponse;
import kg.peaksoft.peaksoftlmsab4.service.TestStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/results")
@CrossOrigin(origins = "*",maxAge = 3600)
public class TestStudentApi {

    private final TestStudentService service;


    @PostMapping("/save")
    public List<TestStudentResponse> createResult(@RequestBody TestStudentRequest testStudentRequest) {

        return service.createResult(testStudentRequest);
    }


}
