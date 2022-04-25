package kg.peaksoft.peaksoftlmsab4.api.controller;

import kg.peaksoft.peaksoftlmsab4.api.payload.TestResultRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestResultResponse;
import kg.peaksoft.peaksoftlmsab4.service.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/results")
//@PreAuthorize("hasAuthority('INSTRUCTOR')")
public class TestResultApi {

    private final TestResultService service;


    @PostMapping("/save")
    public TestResultResponse create(@RequestBody TestResultRequest testResultRequest){
        return service.create(testResultRequest);
    }

    @PutMapping("/update/{id}")
    public TestResultResponse update(@PathVariable Long id,@RequestBody TestResultRequest testResultRequest){
        return service.update(id, testResultRequest);
    }
    @GetMapping("/get/{id}")
    public TestResultResponse finById(@PathVariable Long id){
        return service.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public TestResultResponse deleteById(@PathVariable Long id){
        return service.deleteById(id);
    }
    @GetMapping("/all")
    public List<TestResultResponse> findAll(){
        return service.findAll();
    }

}
