package kg.peaksoft.peaksoftlmsab4.api.controller;

import kg.peaksoft.peaksoftlmsab4.api.payload.TestRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestResponse;
import kg.peaksoft.peaksoftlmsab4.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tests")
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@CrossOrigin(origins = "*",maxAge = 3600)
public class TestApi {

    private final TestService service;

    @PostMapping("/save/{lessonId}")
    public TestResponse create(@RequestBody TestRequest testRequest,@PathVariable Long lessonId){
        return service.create(testRequest,lessonId);
    }

    @PutMapping("/update/{id}")
    public TestResponse update(@PathVariable Long id,@RequestBody TestRequest testRequest){
        return service.update(id, testRequest);
    }
    @GetMapping("/get/{id}")
    public TestResponse finById(@PathVariable Long id){
        return service.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public TestResponse deleteById(@PathVariable Long id){
        return service.deleteById(id);
    }
    @GetMapping("/all")
    public List<TestResponse>findAll(){
        return service.findAll();
    }

}
