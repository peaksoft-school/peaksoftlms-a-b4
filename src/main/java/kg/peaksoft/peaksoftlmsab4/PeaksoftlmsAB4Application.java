package kg.peaksoft.peaksoftlmsab4;

import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.model.entity.UserEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
import kg.peaksoft.peaksoftlmsab4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@SpringBootApplication
@RequiredArgsConstructor
public class PeaksoftlmsAB4Application {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String greetingPage() {
        return "<h1>Welcome to Peaksoftlms-A application !!!<h1/>";
    }

    public static void main(String[] args) {
        SpringApplication.run(PeaksoftlmsAB4Application.class, args);
        System.out.println("Welcome colleagues, project name is Peaksoftlms-A!");
    }

    @PostConstruct
    public void init() {
        UserEntity user = new UserEntity();
        user.setFirstName("Admin");
        user.setLastName("Adminov");

        AuthInfo authInfo = new AuthInfo();
        authInfo.setEmail("admin@gmail.com");
        authInfo.setPassword(passwordEncoder.encode("admin"));
        authInfo.setRole(Role.ADMIN);
        user.setAuthInfo(authInfo);
        System.out.println("------------------------");
        repository.save(user);
        System.out.println(user);
    }
}
