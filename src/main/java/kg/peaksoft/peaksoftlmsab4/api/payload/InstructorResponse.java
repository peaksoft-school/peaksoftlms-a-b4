package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String mobilePhone;
    private String specialization;
    private String email;
}
