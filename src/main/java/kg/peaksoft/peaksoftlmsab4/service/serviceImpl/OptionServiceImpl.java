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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OptionServiceImpl implements OptionService {
    private final OptionEditMapper editMapper;
    private final OptionViewMapper viewMapper;
    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;


    @Override
    public OptionResponse create(Long id, OptionRequest optionRequest) {
        QuestionEntity question = questionRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("not found this id=%s", id)
        ));
        int counter = 0;
        OptionEntity map = modelMapper.map(optionRequest, OptionEntity.class);
        if (question.getQuestionType() == QuestionType.ONE) {
            for (OptionEntity o : question.getOptions()) {
                if (o.getIsTrue()) {
                    counter++;
                }
            }
            if (map.getIsTrue()) {
                if (counter > 0) {
                    throw new BadRequestException("You can't choose multiple variants");
                } else {
                    OptionEntity save = optionRepository.save(map);
                    question.setOptions(save);
                    log.info("successful variant save:{}", save);
                    return viewMapper.viewOption(save);
                }
            } else {
                OptionEntity save = optionRepository.save(map);
                question.setOptions(save);
                log.info("successful variant save:{}", save);
                return viewMapper.viewOption(save);
            }
        }
        OptionEntity save = optionRepository.save(map);
        question.setOptions(save);
        log.info("successful variant save:{}", save);
        return viewMapper.viewOption(optionRepository.save(save));

    }

    @Override
    public OptionResponse update(Long id, OptionRequest optionRequest) {
        OptionEntity option = getByIdMethod(id);
        editMapper.update(option, optionRequest);
        return viewMapper.viewOption(optionRepository.save(option));
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
        option.setQuestion(question);
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
