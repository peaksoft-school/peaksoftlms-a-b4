package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.OptionRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.OptionResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.OptionEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.OptionViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.OptionRepository;
import kg.peaksoft.peaksoftlmsab4.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {
    private final OptionEditMapper editMapper;
    private final OptionViewMapper viewMapper;
    private final OptionRepository optionRepository;
    @Override
    public OptionResponse create(OptionRequest optionRequest) {
        return viewMapper.viewOption(optionRepository.save(editMapper.create(optionRequest)));
    }

    @Override
    public OptionResponse update(Long id, OptionRequest optionRequest) {
        OptionEntity option=optionRepository.findById(id).get();
        editMapper.update(option,optionRequest);
        return viewMapper.viewOption(optionRepository.save(option));
    }

    @Override
    public OptionResponse findById(Long id) {
        return viewMapper.viewOption(optionRepository.findById(id).get());
    }

    @Override
    public OptionResponse deleteById(Long id) {
        OptionEntity option=optionRepository.findById(id).get();
        optionRepository.deleteById(id);
        return viewMapper.viewOption(option);
    }

    @Override
    public List<OptionResponse> findAll() {
        return viewMapper.viewOptions(optionRepository.findAll());
    }
}
