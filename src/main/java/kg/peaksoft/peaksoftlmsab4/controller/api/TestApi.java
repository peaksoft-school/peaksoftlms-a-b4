package kg.peaksoft.peaksoftlmsab4.controller.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.controller.payload.TestRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.TestResponse;
import kg.peaksoft.peaksoftlmsab4.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tests")
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Test API", description = "Test endpoints")
public class TestApi {

    private final TestService service;

    @PostMapping("{lessonId}")
    public TestResponse create(@RequestBody TestRequest testRequest, @PathVariable Long lessonId) {
        return service.create(testRequest, lessonId);
    }

    @PutMapping("{id}")
    public TestResponse update(@PathVariable Long id, @RequestBody TestRequest testRequest) {
        return service.update(id, testRequest);
    }

    @GetMapping("{id}")
    public TestResponse finById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    public TestResponse deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping
    public List<TestResponse> findAll() {
        return service.findAll();
    }

}
