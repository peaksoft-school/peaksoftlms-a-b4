package kg.peaksoft.peaksoftlmsab4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import kg.peaksoft.peaksoftlmsab4.controller.payload.AuthRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.AuthResponse;
import kg.peaksoft.peaksoftlmsab4.service.serviceImpl.AuthServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/api/authentication")
@AllArgsConstructor
@CrossOrigin(origins = "*",maxAge = 3600)
public class AuthApi {

    private final AuthServiceImpl authService;

    @PostMapping
    @PermitAll
    @Operation(summary = "authenticate", description = "it is authenticate http method")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }
}
