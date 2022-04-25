package kg.peaksoft.peaksoftlmsab4.init;

import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.model.entity.UserEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
import kg.peaksoft.peaksoftlmsab4.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@AllArgsConstructor
public class Init {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
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
        repository.save(user);
    }
}
