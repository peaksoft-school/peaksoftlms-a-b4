package kg.peaksoft.peaksoftlmsab4.controller.payload.request;

import kg.peaksoft.peaksoftlmsab4.model.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class StudentRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    @Enumerated(value = EnumType.STRING)
    private StudyFormat studyFormat;
    private Long groupId;
}
