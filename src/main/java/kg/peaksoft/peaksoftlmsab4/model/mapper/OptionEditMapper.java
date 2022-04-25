package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.OptionRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OptionEditMapper {

    public OptionEntity create(OptionRequest optionRequest){
        if (optionRequest==null){
            return null;
        }
        OptionEntity option=new OptionEntity();
        option.setOption(optionRequest.getOption());
        option.setAnswer(optionRequest.isAnswer());

        return option;
    }

    public OptionEntity update(OptionEntity option,OptionRequest optionRequest){
        option.setOption(optionRequest.getOption());
        option.setAnswer(optionRequest.isAnswer());
        return option;
    }
}
