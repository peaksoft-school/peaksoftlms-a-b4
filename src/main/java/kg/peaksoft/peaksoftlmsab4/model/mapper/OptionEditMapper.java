package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.OptionRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.repository.OptionRepository;
import kg.peaksoft.peaksoftlmsab4.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OptionEditMapper {



    public OptionEntity create(OptionRequest optionRequest) {

        OptionEntity option = new OptionEntity();

        option.setAnswer(optionRequest.getAnswer());
        option.setIsTrue(optionRequest.getIsTrue());
        return option;

    }



//    public OptionEntity update(OptionEntity option,OptionRequest optionRequest){
//        option.setIsTrue(optionRequest.getIsTrue());
//        option.setAnswer(optionRequest.getAnswer());
//        return option;
    //  }
}
