package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.peaksoftlmsab4.model.entity.GroupEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CourseResponse {
    private Long id;
    @JsonProperty("course_name")
    private String courseName;
    @JsonProperty("date_of_start")
    private LocalDate dateOfStart;
    private String description;
    private String image;
}
