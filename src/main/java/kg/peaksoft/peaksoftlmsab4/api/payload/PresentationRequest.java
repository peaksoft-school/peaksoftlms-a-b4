package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NotBlank
public class PresentationRequest {
    @JsonProperty("presentation_name")
    private String presentationName;
    private String description;
    @JsonProperty("presentation_file")
    private MultipartFile presentationFile;
}
