package kg.peaksoft.peaksoftlmsab4.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("mobile_phone")
    private String mobilePhone;
    @Enumerated(value = EnumType.STRING)
    private StudyFormat studyFormat;
}
