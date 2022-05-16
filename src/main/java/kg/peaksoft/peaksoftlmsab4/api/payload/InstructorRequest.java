package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NotBlank
public class InstructorRequest {
    private String firstName;
    private String lastName;
    private String mobilePhone;
    private String specialization;
    private String password;
    private String email;

}