package kg.peaksoft.peaksoftlmsab4.controller.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CourseRequest {

    @NotBlank
    private String courseName;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfStart;

    @NotBlank
    private String description;

    @NotBlank
    private String image;

}
