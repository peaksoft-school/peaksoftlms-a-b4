package kg.peaksoft.peaksoftlmsab4.service;


import kg.peaksoft.peaksoftlmsab4.config.jwt.JwtUtils;
import kg.peaksoft.peaksoftlmsab4.dto.authDto.AuthRequest;
import kg.peaksoft.peaksoftlmsab4.dto.authDto.AuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthResponse authenticate(AuthRequest authRequest) {
        Authentication authentication;

        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),
                authRequest.getPassword()
        ));

        String generatedToken = jwtUtils.generateToken(authentication);

        return AuthResponse.builder()
                .email(authRequest.getEmail())
                .token(generatedToken)
                .build();
    }
}
