package kg.peaksoft.peaksoftlmsab4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.controller.payload.request.TestRequest;
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

    @Operation(summary = "Create test", description = "Create test by lesson id")
    @PostMapping("{lessonId}")
    public TestResponse create(@RequestBody TestRequest request, @PathVariable Long lessonId) {
        return service.create(request, lessonId);
    }

    @Operation(summary = "Update test", description = "Update test")
    @PutMapping("{id}")
    public TestResponse update(@PathVariable Long id, @RequestBody TestRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Get test", description = "Get test by id")
    @GetMapping("{id}")
    public TestResponse finById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Delete test", description = "Delete test by id")
    @DeleteMapping("{id}")
    public TestResponse deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @Operation(summary = "Get all tests", description = "Get all tests")
    @GetMapping
    public List<TestResponse> findAll() {
        return service.findAll();
    }

}
