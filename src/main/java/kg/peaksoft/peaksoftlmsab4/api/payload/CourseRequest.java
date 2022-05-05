package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("course_name")
    private String courseName;
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("date_of_start")
    private LocalDate dateOfStart;
    @NotBlank
    private String description;
    @NotBlank
    private String image;
}
