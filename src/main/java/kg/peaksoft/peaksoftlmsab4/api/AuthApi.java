package kg.peaksoft.peaksoftlmsab4.api;

import kg.peaksoft.peaksoftlmsab4.dto.authDto.AuthRequest;
import kg.peaksoft.peaksoftlmsab4.dto.authDto.AuthResponse;
import kg.peaksoft.peaksoftlmsab4.service.AuthService;
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

    private final AuthService authService;

    @PostMapping
    @PermitAll
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }
}
