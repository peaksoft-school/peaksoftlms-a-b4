package kg.peaksoft.peaksoftlmsab4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import kg.peaksoft.peaksoftlmsab4.controller.payload.AuthRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.AuthResponse;
import kg.peaksoft.peaksoftlmsab4.service.serviceImpl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthApi {

    private final AuthServiceImpl authService;

    @Operation(summary = "Login", description = "Any user can authenticate")
    @PostMapping
    public AuthResponse authenticate(@RequestBody AuthRequest request) {
        return authService.authenticate(request);
    }

}
