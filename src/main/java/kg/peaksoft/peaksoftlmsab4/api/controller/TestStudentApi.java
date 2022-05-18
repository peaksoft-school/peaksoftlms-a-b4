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
@CrossOrigin(origins = "*",maxAge = 3600)
public class TestStudentApi {

    private final TestStudentService service;


    @PostMapping("/save")
    public List<TestStudentResponse> createResult(@RequestBody TestStudentRequest testStudentRequest) {

        return service.createResult(testStudentRequest);
    }


}
