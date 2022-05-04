package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class GroupRequest {
    @NotBlank
    @JsonProperty("group_name")
    private String groupName;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("date_of_start")
    private LocalDate dateOfStart;

    @NotBlank
    private String description;
    @NotBlank
    private MultipartFile image;
}
