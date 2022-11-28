package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
public class SimpleResponse {

    private HttpStatus httpStatus;
    private String message;

}