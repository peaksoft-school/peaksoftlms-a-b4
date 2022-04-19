package kg.peaksoft.peaksoftlmsab4.api.controller;

import kg.peaksoft.peaksoftlmsab4.api.payload.AuthRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.AuthResponse;
import kg.peaksoft.peaksoftlmsab4.config.jwt.JwtConfig;
import kg.peaksoft.peaksoftlmsab4.config.jwt.JwtUtils;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.model.entity.CourseEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.UserEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
import kg.peaksoft.peaksoftlmsab4.repository.AuthInfoRepository;
import kg.peaksoft.peaksoftlmsab4.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsab4.service.serviceImpl.AuthServiceImpl;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
class CourseApiTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private static AuthServiceImpl authService;

    @Autowired
    private static PasswordEncoder passwordEncoder;

    @MockBean
    private static AuthInfoRepository authInfoRepository;

    @Autowired
    private JacksonTester<CourseEntity> jacksonTester;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

//    private static String jwt = authService.authenticate(
//            new AuthRequest(
//                    "muhammed@gmail.com",
//                    "123123"
//            )
//    ).getToken();

//    void getJwt(){
//        AuthRequest authRequest = new AuthRequest(
//                    "muhammed@gmail.com",
//                    "123123"
//            );
//        Authentication authentication
//                = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                authRequest.getEmail(),
//                authRequest.getPassword()
//        ));
//
//        String generatedToken = jwtUtils.generateToken(authentication);
//        System.out.println("================>"+generatedToken);
//    }
//
//    private static AuthInfo authInfo = new AuthInfo(
//            "muhammed@gmail.com",
//            "123123",
//            Role.ADMIN,
//            true,
//            true,
//            true,
//            true
//    );
//    private final AuthRequest authRequest = new AuthRequest(
//            "muhammed@gmail.com",
//            "123123"
//    );
//
//    public AuthInfo save(AuthInfo authInfo){
//        final String password = authInfo.getPassword();
//        final String encode = passwordEncoder.encode(password);
//        authInfo.setPassword(encode);
//        return authInfoRepository.save(authInfo);
//    }
//
//    @BeforeEach
//    void setUp() {
//
//        courseRepository.deleteAll();
//        courseRepository.save(new CourseEntity());
//        save(authInfo);
//        AuthResponse authenticate = authService.authenticate(authRequest);
//
//    }
//
//    @Test
//    void testJwt() throws Exception {
//        MockHttpServletResponse response = mvc.perform(get("/api/courses")
//                .header(jwtConfig.getAuthorizationHeader(), jwt)
//                .accept(MediaType.APPLICATION_JSON)
//        ).andReturn().getResponse();
//        response.getContentType().equals(LIST);
//
//    }
//
//    @Test
//    void saveCourse() throws Exception {
////
//        given(courseRepository.getById(1l))
//                .willReturn(new CourseEntity("adlk"));
//                MockHttpServletResponse response = mvc.perform(get("/api/courses/get/{courseId}")
//                        .header(jwtConfig.getAuthorizationHeader())
//                .accept(MediaType.APPLICATION_JSON)
//        ).andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(jacksonTester.write(
//                new CourseEntity()
//        ).getJson());
//    }


    @Test
    void findCourseById() {
        AuthRequest authRequest = new AuthRequest("muhammed@gmail.com","123123");
        Authentication authentication;
        System.out.println("====================>"+authRequest);

        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),
                authRequest.getPassword()
        ));
        String generatedToken = jwtUtils.generateToken(authentication);
        System.out.println("================>"+generatedToken);
    }
//
//    @Test
//    void deleteCourse() {
//    }
//
//    @Test
//    void updateCourse() {
//    }
}