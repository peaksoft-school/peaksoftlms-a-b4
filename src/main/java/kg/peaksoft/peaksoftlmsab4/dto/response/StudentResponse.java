package kg.peaksoft.peaksoftlmsab4.dto.response;

import kg.peaksoft.peaksoftlmsab4.enumPackage.StudyFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {

    private String id;
    private String firstName;
    private String mobilePhone;
    private StudyFormat studyFormat;
    private String email;
}
