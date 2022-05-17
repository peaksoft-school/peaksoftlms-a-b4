package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.OptionRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.OptionResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class OptionMapper {



    public OptionEntity create(OptionRequest optionRequest) {

        OptionEntity option = new OptionEntity();

        option.setAnswer(optionRequest.getAnswer());
        option.setIsTrue(optionRequest.getIsTrue());
        return option;

    }



    public OptionEntity update(OptionEntity option,OptionRequest optionRequest){
        option.setIsTrue(optionRequest.getIsTrue());
        option.setAnswer(optionRequest.getAnswer());
        return option;
      }

    public OptionResponse viewOption(OptionEntity option) {
        if (option == null) {
            log.error("The option db is null!");
            return null;
        }
        OptionResponse optionResponse = new OptionResponse();
        if (option.getId() != null) {
            optionResponse.setId(option.getId());
        }
        optionResponse.setIsTrue(option.getIsTrue());
        optionResponse.setAnswer(option.getAnswer());
        return optionResponse;
    }

    public List<OptionResponse> viewOptions(List<OptionEntity> options) {

        List<OptionResponse> optionResponses = new ArrayList<>();
        for (OptionEntity o : options) {
            optionResponses.add(viewOption(o));
        }
        return optionResponses;
    }
}
