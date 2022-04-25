package kg.peaksoft.peaksoftlmsab4.api.payload;

import kg.peaksoft.peaksoftlmsab4.model.entity.TestResultEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OptionResponse {

    private Long id;
    private String option;
    private boolean answer;

    private List<TestResultEntity>resultEntities;
}
