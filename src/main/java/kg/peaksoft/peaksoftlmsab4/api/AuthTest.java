//package kg.peaksoft.peaksoftlmsab4.api;
//
//import kg.peaksoft.peaksoftlmsab4.dto.request.AuthRequest;
//import kg.peaksoft.peaksoftlmsab4.dto.response.AuthResponse;
//import kg.peaksoft.peaksoftlmsab4.model.entity.Admin;
//import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
//import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
//import kg.peaksoft.peaksoftlmsab4.repository.AdminRepository;
//import kg.peaksoft.peaksoftlmsab4.service.serviceImpl.AuthService;
//import lombok.AllArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.security.PermitAll;
//
//@RestController
//@RequestMapping("api/auth")
//@AllArgsConstructor
//public class AuthTest {
//
//    private AuthService authService;
//    private final AdminRepository repository;
//    private final PasswordEncoder passwordEncoder;
//
//    @PostMapping
//    @PermitAll
//    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
//        return authService.authenticate(authRequest);
//    }
//
//
//    @PostConstruct
//    public void init() {
//        Admin admin = new Admin();
//        admin.setFirstName("Nurmuhammad");
//        admin.setLastName("Babaev");
//
//        AuthInfo authInfo = new AuthInfo();
//        authInfo.setEmail("muhammed@gmail.com");
//        authInfo.setPassword(passwordEncoder.encode("123123"));
//        authInfo.setRole(Role.ADMIN);
//        admin.setAuthInfo(authInfo);
//        repository.save(admin);
//    }
//}