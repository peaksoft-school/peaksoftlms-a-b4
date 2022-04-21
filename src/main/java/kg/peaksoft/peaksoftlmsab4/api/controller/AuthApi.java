package kg.peaksoft.peaksoftlmsab4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import kg.peaksoft.peaksoftlmsab4.api.payload.AuthRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.AuthResponse;
import kg.peaksoft.peaksoftlmsab4.service.serviceImpl.AuthServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/authentication")
@AllArgsConstructor
public class AuthApi {

    private final AuthServiceImpl authService;

    @PostMapping
    @PreAuthorize("permitAll()")
    @Operation(summary = "authenticate", description = "it is authenticate http method")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }
    @RolesAllowed("ADMIN")
    @GetMapping("/test")
    public String testToken(){
        return "Tested";
    }
}
