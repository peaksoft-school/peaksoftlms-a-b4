package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.PresentationRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.PresentationResponse;

import java.util.List;

public interface PresentationService {

    PresentationResponse create(PresentationRequest request, Long lessonId);

    List<PresentationResponse> getAll();

    PresentationResponse getById(Long presentationId);

    PresentationResponse update(Long presentationId, PresentationRequest request);

    PresentationResponse deleteById(Long presentationId);

}
