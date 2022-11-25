package kg.peaksoft.peaksoftlmsab4.controller.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.controller.payload.QuestionRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.QuestionResponse;
import kg.peaksoft.peaksoftlmsab4.service.QuestionService;
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
@RequestMapping("api/questions")
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Question API", description = "Question endpoints")
public class QuestionApi {

    private final QuestionService service;

    @PostMapping
    public QuestionResponse create(@PathVariable Long id, @RequestBody QuestionRequest questionRequest) {
        return service.create(id, questionRequest);
    }

    @PutMapping("{id}")
    public QuestionResponse update(@PathVariable Long id, @RequestBody QuestionRequest questionRequest) {
        return service.update(id, questionRequest);
    }

    @PutMapping("{testId}/set/{questionId}")
    public QuestionResponse findByQuestionToTest(@PathVariable Long testId, @PathVariable Long questionId) {
        return service.findByQuestionToTest(testId, questionId);
    }

    @GetMapping("{id}")
    public QuestionResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    public QuestionResponse deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping
    public List<QuestionResponse> findAll() {
        return service.findAll();
    }

}
