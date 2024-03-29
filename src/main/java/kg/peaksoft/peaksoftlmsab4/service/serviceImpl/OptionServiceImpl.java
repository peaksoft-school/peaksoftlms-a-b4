package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.OptionRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.OptionResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.QuestionEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.OptionMapper;
import kg.peaksoft.peaksoftlmsab4.repository.OptionRepository;
import kg.peaksoft.peaksoftlmsab4.service.OptionService;
import kg.peaksoft.peaksoftlmsab4.validation.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OptionServiceImpl implements OptionService {

    private final OptionMapper optionMapper;
    private final OptionRepository optionRepository;

    public OptionResponse create(OptionRequest request) {
        QuestionEntity question = new QuestionEntity();
        OptionEntity optionEntity = optionMapper.create(request);
        OptionEntity save = optionRepository.save(optionEntity);
        question.setOption(save);
        log.info("successful variant save:{}", save);
        return optionMapper.viewOption(optionRepository.save(save));
    }

    @Override
    public OptionResponse update(Long id, OptionRequest request) {
        OptionEntity option = optionRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Question with id = %s does not exists", id)));
        optionMapper.update(option, request);
        return optionMapper.viewOption(optionRepository.save(option));
    }

    @Override
    public OptionResponse findById(Long id) {
        return optionMapper.viewOption(optionRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Option with id = %s does not exists", id))));
    }

    @Override
    public OptionResponse deleteById(Long id) {
        OptionEntity option = optionRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Option with id = %s does not exists", id)));
        optionRepository.deleteById(id);
        return optionMapper.viewOption(option);
    }

    @Override
    public List<OptionResponse> findAll() {
        return optionMapper.viewOptions(optionRepository.findAll());
    }

    @Override
    public OptionResponse findByOptionToQuestion(Long questionId, Long optionId) {
        OptionEntity option = getByIdMethod(optionId);
        return optionMapper.viewOption(optionRepository.save(option));
    }

    private OptionEntity getByIdMethod(Long optionId) {
        return optionRepository.findById(optionId).orElseThrow(() -> {
            log.error("Question with id = {} does not exists", optionId);
            throw new NotFoundException(String.format("Question with id = %s does not exists", optionId));
        });
    }

}
