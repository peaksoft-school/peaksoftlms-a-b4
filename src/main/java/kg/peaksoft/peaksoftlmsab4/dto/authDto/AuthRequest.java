package kg.peaksoft.peaksoftlmsab4.dto.authDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String email;
    private String password;
}
