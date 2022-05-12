package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.OptionRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.OptionResponse;
import kg.peaksoft.peaksoftlmsab4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.QuestionEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.QuestionType;
import kg.peaksoft.peaksoftlmsab4.model.mapper.OptionEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.OptionViewMapper;
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
    private final OptionEditMapper editMapper;
    private final OptionViewMapper viewMapper;
    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;


    public OptionResponse create( OptionRequest optionRequest) {
        QuestionEntity question=new QuestionEntity();
        OptionEntity optionEntity = editMapper.create(optionRequest);
        OptionEntity save = optionRepository.save(optionEntity);
        question.setOption(save);
        log.info("successful variant save:{}", save);
        return viewMapper.viewOption(optionRepository.save(save));


    }


    @Override
    public OptionResponse update(Long id, OptionRequest optionRequest) {
        //   OptionEntity option = getByIdMethod(id);
        //  editMapper.update(option, optionRequest);
        //  return viewMapper.viewOption(optionRepository.save(option));
        return null;
    }

    @Override
    public OptionResponse findById(Long id) {
        return viewMapper.viewOption(optionRepository.findById(id).get());
    }

    @Override
    public OptionResponse deleteById(Long id) {
        OptionEntity option = optionRepository.findById(id).get();
        optionRepository.deleteById(id);
        return viewMapper.viewOption(option);
    }

    @Override
    public List<OptionResponse> findAll() {
        return viewMapper.viewOptions(optionRepository.findAll());
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
//        option.setQuestion(question);
        return viewMapper.viewOption(optionRepository.save(option));
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
