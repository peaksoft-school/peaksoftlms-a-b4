package kg.peaksoft.peaksoftlmsab4.api.controller;

import kg.peaksoft.peaksoftlmsab4.api.payload.QuestionRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.QuestionResponse;
import kg.peaksoft.peaksoftlmsab4.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/question")
@PreAuthorize("hasAuthority('INSTRUCTOR')")
public class QuestionApi {

    private final QuestionService service;

    @PostMapping("/save")
    public QuestionResponse create(@PathVariable Long id,@RequestBody QuestionRequest questionRequest) {
        return service.create(id,questionRequest);
    }

    @PutMapping("/update/{id}")
    public QuestionResponse update(@PathVariable Long id,@RequestBody QuestionRequest questionRequest) {
        return service.update(id, questionRequest);
    }
    @PutMapping("/{testId}/set/{questionId}")
    public QuestionResponse findByQuestionToTest(@PathVariable Long testId,@PathVariable Long questionId){
        return service.findByQuestionToTest(testId,questionId);
    }

    @GetMapping("/get/{id}")
    public QuestionResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public QuestionResponse deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/all")
    public List<QuestionResponse> findAll() {
        return service.findAll();
    }
}
