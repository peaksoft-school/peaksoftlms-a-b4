package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.PresentationRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.PresentationResponse;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.PresentationEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.VideoEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.PresentationMapper;
import kg.peaksoft.peaksoftlmsab4.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsab4.repository.PresentationRepository;
import kg.peaksoft.peaksoftlmsab4.service.PresentationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PresentationServiceImpl implements PresentationService {

    private final PresentationRepository presentationRepository;
    private final PresentationMapper mapper;
    private final LessonRepository lessonRepository;

    @Override
    public PresentationResponse create(PresentationRequest request, Long lessonId) {
        LessonEntity lesson = lessonRepository.getById(lessonId);
        PresentationEntity presentationEntity = presentationRepository.save(mapper.mapToEntity(request,null));
        presentationEntity.setLessonEntity(lesson);
        return mapper.mapToResponse(presentationEntity);
    }

    @Override
    public List<PresentationResponse> getAll() {
        List<PresentationResponse> presentationResponses = new ArrayList<>();
        for (PresentationEntity presentationEntity:presentationRepository.findAll()) {
            presentationResponses.add(mapper.mapToResponse(presentationEntity));
        }
        log.info("fount {} presentations",presentationResponses.size());
        return presentationResponses;
    }

    @Override
    public PresentationResponse getById(Long presentationId) {
        PresentationEntity presentationEntity = presentationRepository.findById(presentationId)
                .orElseThrow(()-> {
                    throw new NotFoundException(String.format("presentation with id = %s does not exists",presentationId));
                });
        return mapper.mapToResponse(presentationEntity);
    }

    @Override
    public PresentationResponse update(Long presentationId, PresentationRequest presentationRequest) {
        return mapper.mapToResponse(presentationRepository.save(mapper.mapToEntity(presentationRequest,presentationId)));
    }

    @Override
    public void deleteById(Long presentationId) {
        PresentationEntity presentationEntity = presentationRepository.findById(presentationId)
                .orElseThrow(()-> {
                    throw new NotFoundException(String.format("presentation with id = %s does not exists",presentationId));
                });
        presentationRepository.delete(presentationEntity);
    }
}
