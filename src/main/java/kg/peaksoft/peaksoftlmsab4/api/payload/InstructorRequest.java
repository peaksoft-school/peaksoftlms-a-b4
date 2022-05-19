package kg.peaksoft.peaksoftlmsab4.api.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NotBlank
public class InstructorRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;
    private String password;
    private String email;

    private Long courseId;

}