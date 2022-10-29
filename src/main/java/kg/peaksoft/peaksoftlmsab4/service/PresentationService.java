package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.PresentationRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.PresentationResponse;

import java.util.List;

public interface PresentationService {
    PresentationResponse create(PresentationRequest presentationRequest, Long lessonId);

    List<PresentationResponse> getAll();

    PresentationResponse getById(Long presentationId);

    PresentationResponse update(Long presentationId, PresentationRequest presentationRequest);

    PresentationResponse deleteById(Long presentationId);
}
