package kg.peaksoft.peaksoftlmsab4.api.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorResponse {
    private String id;
    private String fullName;
    private String phoneNumber;
    private String specialization;
    private String email;
}
