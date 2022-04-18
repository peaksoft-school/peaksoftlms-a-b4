package kg.peaksoft.peaksoftlmsab4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GroupResponse {

    private Long id;

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("date_of_start")
    private LocalDate dateOfStart;

    private String description;
    private String image;

}
