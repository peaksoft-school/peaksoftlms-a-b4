package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.OptionRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.OptionResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OptionMapper {

    public OptionEntity create(OptionRequest optionRequest) {
        OptionEntity option = new OptionEntity();
        option.setOption(optionRequest.getOption());
        option.setIsTrue(optionRequest.getIsTrue());
        return option;
    }

    public OptionEntity update(OptionEntity option, OptionRequest optionRequest) {
        option.setIsTrue(optionRequest.getIsTrue());
        option.setOption(optionRequest.getOption());
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
        optionResponse.setOption(option.getOption());
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
