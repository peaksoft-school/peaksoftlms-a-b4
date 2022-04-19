package kg.peaksoft.peaksoftlmsab4;

import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.model.entity.UserEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
import kg.peaksoft.peaksoftlmsab4.repository.UserRepository;
import kg.peaksoft.peaksoftlmsab4.service.serviceImpl.AuthServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication

public class PeaksoftlmsAB4Application {
//    private  UserRepository repository;
//    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(PeaksoftlmsAB4Application.class, args);
        System.out.println("Welcome colleagues, project name is Peaksoftlms-A!");
    }

//    @PostConstruct
//    public void init() {
//        UserEntity user = new UserEntity();
//        user.setFirstName("Nurmuhammad");
//        user.setLastName("Babaev");
//
//        AuthInfo authInfo = new AuthInfo();
//        authInfo.setEmail("muhammed@gmail.com");
//        authInfo.setPassword(passwordEncoder.encode("123123"));
//        authInfo.setRole(Role.ADMIN);
//        user.setAuthInfo(authInfo);
//        repository.save(user);
//    }
}
