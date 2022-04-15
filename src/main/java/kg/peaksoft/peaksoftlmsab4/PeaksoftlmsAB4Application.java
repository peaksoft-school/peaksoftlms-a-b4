package kg.peaksoft.peaksoftlmsab4;

import kg.peaksoft.peaksoftlmsab4.model.entity.Admin;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
import kg.peaksoft.peaksoftlmsab4.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
@AllArgsConstructor
public class PeaksoftlmsAB4Application {

    private final AdminRepository repository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(PeaksoftlmsAB4Application.class, args);
        System.out.println("Welcome colleagues, project name is Peaksoftlms-A!");
    }

    @PostConstruct
    public void init() {
        Admin admin = new Admin();
        admin.setFirstName("Nurmuhammad");
        admin.setLastName("Babaev");

        AuthInfo authInfo = new AuthInfo();
        authInfo.setEmail("muhammed@gmail.com");
        authInfo.setPassword(passwordEncoder.encode("123123"));
        authInfo.setRole(Role.ADMIN);
        admin.setAuthInfo(authInfo);
        repository.save(admin);
    }
}
