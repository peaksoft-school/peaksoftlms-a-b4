package kg.peaksoft.peaksoftlmsab4.dto.request;

import kg.peaksoft.peaksoftlmsab4.enumPackage.StudyFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NotBlank
public class StudentRequest {

    private String firstName;
    private String lastName;
    private String mobilePhone;
    private StudyFormat studyFormat;
    private String password;
    private String email;
}
