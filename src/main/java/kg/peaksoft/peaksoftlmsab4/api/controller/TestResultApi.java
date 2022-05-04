package kg.peaksoft.peaksoftlmsab4.api.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kg.peaksoft.peaksoftlmsab4.api.payload.ResultResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestResultRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestResultResponse;
import kg.peaksoft.peaksoftlmsab4.service.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/results")
//@PreAuthorize("hasAuthority('INSTRUCTOR')")
public class TestResultApi {

    private final TestResultService service;

    @PostMapping
    public TestResultResponse create(@RequestBody TestResultRequest testResultRequest) {
        return service.create(testResultRequest);
    }

//    @PutMapping("/update/{id}")
//    public TestResultResponse update(@PathVariable Long id, @RequestBody TestResultRequest testResultRequest) {
//        return service.update(id, testResultRequest);
//    }

    @GetMapping("/get")
    public ResultResponse result() {
        return service.result();
    }


}
