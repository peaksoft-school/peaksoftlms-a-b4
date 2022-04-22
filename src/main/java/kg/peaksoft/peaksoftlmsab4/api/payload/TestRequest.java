package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TestRequest {
    @NotNull
    @JsonProperty("test_name")
    private String testName;
}
