package kg.peaksoft.peaksoftlmsab4.api.payload;

import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerRequest {
    private List<Long> optionId;
}
