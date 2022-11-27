package kg.peaksoft.peaksoftlmsab4.controller.payload.response;

import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthResponse {

    private String firstName;
    private String lastName;
    private Role role;
    private String email;
    private String token;

}
