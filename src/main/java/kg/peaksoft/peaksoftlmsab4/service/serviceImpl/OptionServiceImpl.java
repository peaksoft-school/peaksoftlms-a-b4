package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.controller.payload.OptionRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.OptionResponse;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.QuestionEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.OptionMapper;
import kg.peaksoft.peaksoftlmsab4.repository.OptionRepository;
import kg.peaksoft.peaksoftlmsab4.repository.QuestionRepository;
import kg.peaksoft.peaksoftlmsab4.service.OptionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OptionServiceImpl implements OptionService {
    private final OptionMapper optionMapper;

    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;


    public OptionResponse create( OptionRequest optionRequest) {
        QuestionEntity question=new QuestionEntity();
        OptionEntity optionEntity = optionMapper.create(optionRequest);
        OptionEntity save = optionRepository.save(optionEntity);
        question.setOption(save);
        log.info("successful variant save:{}", save);
        return optionMapper.viewOption(optionRepository.save(save));


    }
    @Override
    public OptionResponse update(Long id, OptionRequest optionRequest) {
      OptionEntity option=optionRepository.findById(id).orElseThrow(()-> new NotFoundException(
              String.format("Question with id = %s does not exists",id)
      ));
      optionMapper.update(option,optionRequest);

        return optionMapper.viewOption(optionRepository.save(option));
    }

    @Override
    public OptionResponse findById(Long id) {
        return optionMapper.viewOption(optionRepository.findById(id).orElseThrow(()-> new NotFoundException(
                String.format("Option with id = %s does not exists",id))));
    }

    @Override
    public OptionResponse deleteById(Long id) {
        OptionEntity option = optionRepository.findById(id).orElseThrow(()-> new NotFoundException(
                String.format("Option with id = %s does not exists",id)));
        optionRepository.deleteById(id);
        return optionMapper.viewOption(option);
    }

    @Override
    public List<OptionResponse> findAll() {
        return optionMapper.viewOptions(optionRepository.findAll());
    }

    @Override
    public OptionResponse findByOptionToQuestion(Long questionId, Long optionId) {
        QuestionEntity question = questionRepository.findById(questionId)
                .orElseThrow(() -> {
                    log.error("Test with id = {} does not exists", questionId);
                    throw new NotFoundException(
                            String.format("Test with id = %s does not exists", questionId)
                    );
                });
        OptionEntity option = getByIdMethod(optionId);

        return optionMapper.viewOption(optionRepository.save(option));
    }

    private OptionEntity getByIdMethod(Long optionId) {
        return optionRepository.findById(optionId).
                orElseThrow(() -> {
                    log.error("Question with id = {} does not exists", optionId);
                    throw new NotFoundException(
                            String.format("Question with id = %s does not exists", optionId)
                    );
                });
    }
}
