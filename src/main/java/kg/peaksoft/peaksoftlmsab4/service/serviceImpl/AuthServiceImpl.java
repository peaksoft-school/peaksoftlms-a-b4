package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.AuthRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.AuthResponse;
import kg.peaksoft.peaksoftlmsab4.config.jwt.JwtUtils;
import kg.peaksoft.peaksoftlmsab4.validation.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.model.entity.InstructorEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.UserEntity;
import kg.peaksoft.peaksoftlmsab4.repository.AuthInfoRepository;
import kg.peaksoft.peaksoftlmsab4.repository.InstructorRepository;
import kg.peaksoft.peaksoftlmsab4.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsab4.repository.UserRepository;
import kg.peaksoft.peaksoftlmsab4.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final AuthInfoRepository authInfoRepository;
    private final UserRepository userRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;


    public AuthResponse authenticate(AuthRequest request) {
        Authentication authentication;

        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        String generatedToken = jwtUtils.generateToken(authentication);

        AuthInfo authInfo = authInfoRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.error("User with email = {} does not exist", request.getEmail());
                    throw new NotFoundException(
                            String.format("User with email = %s does not exist", request.getEmail())
                    );
                });

        String firstName = "";
        String lastName = "";
        for (UserEntity u : userRepository.findAll()) {
            if (u.getAuthInfo().getEmail().equals(authInfo.getEmail())) {
                firstName = u.getFirstName();
                lastName = u.getLastName();
            }
            for (InstructorEntity i : instructorRepository.findAll()) {
                if (i.getAuthInfo().getEmail().equals(authInfo.getEmail())) {
                    firstName = i.getFirstName();
                    lastName = i.getLastName();
                }
                for (StudentEntity s : studentRepository.findAll()) {
                    if (s.getAuthInfo().getEmail().equals(authInfo.getEmail())) {
                        firstName = s.getFirstName();
                        lastName = s.getLastName();
                    }
                }
            }
        }

        return AuthResponse.builder()
                .firstName(firstName)
                .lastName(lastName)
                .role(authInfo.getRole())
                .email(request.getEmail())
                .token(generatedToken)
                .build();
    }
}
