package kg.peaksoft.peaksoftlmsab4.api;

import io.swagger.v3.oas.annotations.Operation;
import kg.peaksoft.peaksoftlmsab4.dto.auth.AuthRequest;
import kg.peaksoft.peaksoftlmsab4.dto.auth.AuthResponse;
import kg.peaksoft.peaksoftlmsab4.service.serviceImpl.AuthServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/api/authentication")
@AllArgsConstructor
public class AuthApi {

    private final AuthServiceImpl authService;

    @PostMapping
    @PermitAll
    @Operation(summary = "authenticate", description = "it is authenticate http method")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }
}
