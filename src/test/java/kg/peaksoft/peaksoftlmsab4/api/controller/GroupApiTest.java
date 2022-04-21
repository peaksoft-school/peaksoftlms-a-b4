package kg.peaksoft.peaksoftlmsab4.api.controller;

import kg.peaksoft.peaksoftlmsab4.api.payload.AuthRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.AuthResponse;
import kg.peaksoft.peaksoftlmsab4.config.jwt.JwtUtils;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.repository.AuthInfoRepository;
import kg.peaksoft.peaksoftlmsab4.service.serviceImpl.AuthServiceImpl;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GroupApiTest {

    @MockBean
    private AuthInfoRepository authInfoRepository;

    @MockBean
    private AuthServiceImpl authService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private AuthenticationManager authenticationManager;

//    AuthResponse getToken(){
//        AuthInfo authInfo = authInfoRepository.getById(2l);
//        AuthRequest authRequest = new AuthRequest(authInfo.getEmail(),authInfo.getPassword());
//        System.out.println(authRequest.getEmail());
//        return authService.authenticate(authRequest);
//    }

//    @Test
//    void create() {
//        getToken();
//    }
    
    @Test
    void deleteById() {
    }
    @Test
    void update() {
    }

    @Test
    void getById() {
    }
    @Test
    void getAllGroup() {
    }
}