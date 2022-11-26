package kg.peaksoft.peaksoftlmsab4.controller.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GroupResponse {

    private Long id;
    private String groupName;
    private LocalDate dateOfStart;
    private String description;
    private String image;

}
