package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.OptionRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.OptionResponse;

import java.util.List;

public interface OptionService {

    OptionResponse create(OptionRequest request);

    OptionResponse update(Long id, OptionRequest request);

    OptionResponse findById(Long id);

    OptionResponse deleteById(Long id);

    List<OptionResponse> findAll();

    OptionResponse findByOptionToQuestion(Long questionId, Long optionId);

}
