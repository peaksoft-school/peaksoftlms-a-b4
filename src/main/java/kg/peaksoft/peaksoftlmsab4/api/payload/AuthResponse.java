package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthResponse {

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private Role role;
    private String email;
    private String token;
}
