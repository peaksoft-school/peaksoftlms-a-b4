package kg.peaksoft.peaksoftlmsab4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.controller.payload.request.AnswerRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.AnswerResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.service.serviceImpl.AnswerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/answers")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Answer API", description = "Answer endpoints")
public class AnswerApi {

    private final AnswerServiceImpl answerService;

    @Operation(summary = "Student answer", description = "Student answering")
    @PostMapping
    public void create(@RequestBody AnswerRequest request, Authentication authentication) {
        AuthInfo authInfo = (AuthInfo) authentication.getPrincipal();
        answerService.createAnswers(request, authInfo);
    }

    @Operation(summary = "Test result", description = "Instructor can see the test result")
    @GetMapping
    public List<AnswerResponse> resultTest() {
        return answerService.resultTest();
    }

}
