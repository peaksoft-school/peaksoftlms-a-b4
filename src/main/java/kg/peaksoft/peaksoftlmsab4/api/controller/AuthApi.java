package kg.peaksoft.peaksoftlmsab4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import kg.peaksoft.peaksoftlmsab4.api.payload.AuthRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.AuthResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.model.entity.UserEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
import kg.peaksoft.peaksoftlmsab4.repository.UserRepository;
import kg.peaksoft.peaksoftlmsab4.service.serviceImpl.AuthServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/authentication")
@AllArgsConstructor
public class AuthApi {

    private UserRepository repository;
    private PasswordEncoder passwordEncoder;
    private final AuthServiceImpl authService;

    @PostMapping
    @PermitAll
    @Operation(summary = "authenticate", description = "it is authenticate http method")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }
    @RolesAllowed("ADMIN")
    @GetMapping("/test")
    public String testToken(){
        return "Tested";
    }

    @PostConstruct
    public void init() {
        UserEntity user = new UserEntity();
        user.setFirstName("Nurmuhammad");
        user.setLastName("Babaev");

        AuthInfo authInfo = new AuthInfo();
        authInfo.setEmail("muhammed@gmail.com");
        authInfo.setPassword(passwordEncoder.encode("123123"));
        authInfo.setRole(Role.ADMIN);
        user.setAuthInfo(authInfo);
        repository.save(user);
    }
}
