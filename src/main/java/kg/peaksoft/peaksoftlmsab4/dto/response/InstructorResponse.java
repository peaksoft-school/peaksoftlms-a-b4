package kg.peaksoft.peaksoftlmsab4.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorResponse {
    private String id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("mobile_phone")
    private String mobilePhone;
    private String specialization;
    private String email;
}

