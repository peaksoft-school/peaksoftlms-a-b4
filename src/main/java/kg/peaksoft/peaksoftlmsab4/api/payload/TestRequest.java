package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class TestRequest {

    @JsonProperty("test_name")
    private String testName;
}
