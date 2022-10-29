package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.OptionRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.OptionResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OptionService {
    OptionResponse create(OptionRequest optionRequest);

    OptionResponse update(Long id, OptionRequest optionRequest);

    OptionResponse findById(Long id);

    OptionResponse deleteById(Long id);

    List<OptionResponse> findAll();

    OptionResponse findByOptionToQuestion(Long questionId, Long optionId);
}
