package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.AuthRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.AuthResponse;
import kg.peaksoft.peaksoftlmsab4.config.jwt.JwtUtils;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.repository.AuthInfoRepository;
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


    public AuthResponse authenticate(AuthRequest authRequest) {
        Authentication authentication;

        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),
                authRequest.getPassword()
        ));

        String generatedToken = jwtUtils.generateToken(authentication);

        AuthInfo authInfo = authInfoRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> {
                    log.error("Course with email = {} does not exists", authRequest.getEmail());
                    throw new NotFoundException(
                            String.format("Course with email = %s does not exists", authRequest.getEmail())
                    );
                });

        return AuthResponse.builder()
                .role(authInfo.getRole())
                .email(authRequest.getEmail())
                .token(generatedToken)
                .build();
    }
}
