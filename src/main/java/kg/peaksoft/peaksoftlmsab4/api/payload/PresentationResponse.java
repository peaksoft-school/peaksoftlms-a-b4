package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PresentationResponse {
    Long id;
    @JsonProperty("presentation_name")
    private String presentationName;
    private String description;
    @JsonProperty("presentation_link")
    private String presentationLink;
}
