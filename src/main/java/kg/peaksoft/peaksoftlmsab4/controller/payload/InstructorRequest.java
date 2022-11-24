package kg.peaksoft.peaksoftlmsab4.controller.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class InstructorRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String specialization;
    @NotBlank
    private String password;
    @NotBlank
    private String email;

    private Long courseId;

}