package kg.peaksoft.peaksoftlmsab4.api;

import kg.peaksoft.peaksoftlmsab4.model.enams.Authority;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.repositories.AuthInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthTest {

    private final AuthInfoRepository repository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/test")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'STUDENT')")
    public Map<String, String> test(Authentication authentication) {
        AuthInfo authInfo = (AuthInfo) authentication.getPrincipal();
        return Map.of(
                "email", authentication.getName(),
                "authority", authInfo.getAuthority().name()
        );
    }

    @PostConstruct
    public void init() {
        AuthInfo authInfo = new AuthInfo();
        authInfo.setEmail("muhammed@gmail.com");
        authInfo.setPassword(passwordEncoder.encode("123123"));
        authInfo.setAuthority(Authority.ADMIN);
        repository.save(authInfo);

    }
}