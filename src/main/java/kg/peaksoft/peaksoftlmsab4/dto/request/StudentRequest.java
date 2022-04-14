package kg.peaksoft.peaksoftlmsab4.dto.request;

import kg.peaksoft.peaksoftlmsab4.enumPackage.StudyFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NotBlank
public class StudentRequest {

    private String firstName;
    private String lastName;
    private String mobilePhone;
    @Enumerated(value = EnumType.STRING)
    private StudyFormat studyFormat;
}
