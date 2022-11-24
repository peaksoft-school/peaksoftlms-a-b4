package kg.peaksoft.peaksoftlmsab4.controller.payload;

import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
import kg.peaksoft.peaksoftlmsab4.model.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
    private String id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private StudyFormat studyFormat;
    private String groupName;
    private Role role;
}
