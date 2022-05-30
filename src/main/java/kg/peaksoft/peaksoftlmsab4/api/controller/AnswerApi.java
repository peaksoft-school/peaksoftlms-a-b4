package kg.peaksoft.peaksoftlmsab4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import kg.peaksoft.peaksoftlmsab4.api.payload.AnswerRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.AnswerResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.service.serviceImpl.AnswerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/api/answers")
@AllArgsConstructor
@PreAuthorize("hasAuthority('STUDENT')")
@CrossOrigin(origins = "*",maxAge = 3600)
public class AnswerApi {

    private final AnswerServiceImpl answerService;

    @PostMapping
    @PermitAll
    @Operation(summary = "authenticate", description = "it is authenticate http method")
    public void create(@RequestBody AnswerRequest answerRequest,Authentication authentication) {
        AuthInfo authInfo = (AuthInfo) authentication.getPrincipal();
        answerService.createAnswers(answerRequest,authInfo);
    }
    @GetMapping
    @PermitAll
    @Operation(summary = "authenticate", description = "it is authenticate http method")
    public AnswerResponse resultTest(Authentication authentication) {
        AuthInfo authInfo = (AuthInfo) authentication.getPrincipal();
        return answerService.resultTest(authInfo);
    }
}
