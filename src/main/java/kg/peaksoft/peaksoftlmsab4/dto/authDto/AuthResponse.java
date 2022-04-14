package kg.peaksoft.peaksoftlmsab4.dto.authDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthResponse {
    private String email;
    private String token;
}
