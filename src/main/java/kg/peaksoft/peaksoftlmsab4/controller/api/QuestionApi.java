package kg.peaksoft.peaksoftlmsab4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.controller.payload.request.QuestionRequest;
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

    @Operation(summary = "Create question", description = "Save a new question")
    @PostMapping
    public QuestionResponse create(@PathVariable Long id, @RequestBody QuestionRequest request) {
        return service.create(id, request);
    }

    @Operation(summary = "Update question", description = "Update a existing question")
    @PutMapping("{id}")
    public QuestionResponse update(@PathVariable Long id, @RequestBody QuestionRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Set question to test", description = "Setting question to test")
    @PutMapping("{testId}/set/{questionId}")
    public QuestionResponse findByQuestionToTest(@PathVariable Long testId, @PathVariable Long questionId) {
        return service.findByQuestionToTest(testId, questionId);
    }

    @Operation(summary = "Get question", description = "Get a existing question by id")
    @GetMapping("{id}")
    public QuestionResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Delete question", description = "Delete a existing question by id")
    @DeleteMapping("{id}")
    public QuestionResponse deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @Operation(summary = "Get all questions", description = "Get all existing questions")
    @GetMapping
    public List<QuestionResponse> findAll() {
        return service.findAll();
    }

}
