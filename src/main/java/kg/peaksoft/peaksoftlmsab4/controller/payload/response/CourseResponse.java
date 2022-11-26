package kg.peaksoft.peaksoftlmsab4.controller.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseResponse {
    private Long id;
    private String courseName;
    private LocalDate dateOfStart;
    private String description;
    private String image;
}
