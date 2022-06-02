package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.PresentationRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.PresentationResponse;
import kg.peaksoft.peaksoftlmsab4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.PresentationEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.PresentationMapper;
import kg.peaksoft.peaksoftlmsab4.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsab4.repository.PresentationRepository;
import kg.peaksoft.peaksoftlmsab4.service.PresentationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        if (lesson.getPresentationEntity() == null) {
            PresentationEntity presentationEntity = mapper.mapToEntity(request);
            presentationEntity.setLessonEntity(lesson);
            lesson.setPresentationEntity(presentationEntity);
            PresentationEntity savedPresentationEntity = presentationRepository.save(presentationEntity);
            return mapper.mapToResponse(savedPresentationEntity);
        } else {
            throw new BadRequestException("In this lesson presentation already exists");
        }
    }
    @Override
    public List<PresentationResponse> getAll() {
        log.info("fount {} presentations", presentationRepository.findAll().size());
        return mapper.mapToResponse(presentationRepository.findAll());
    }

    @Override
    public PresentationResponse getById(Long presentationId) {
        PresentationEntity presentationEntity = presentationRepository.findById(presentationId)
                .orElseThrow(() -> {
                    throw new NotFoundException(String.format("presentation with id = %s does not exists", presentationId));
                });
        return mapper.mapToResponse(presentationEntity);
    }

    @Override
    public PresentationResponse update(Long presentationId, PresentationRequest presentationRequest) {
        PresentationEntity presentationEntity = presentationRepository.findById(presentationId)
                .orElseThrow(() -> {
                    throw new NotFoundException(String.format("Presentation with id = %s does not exists", presentationId));
                });
        mapper.update(presentationEntity, presentationRequest);
        presentationRepository.save(presentationEntity);
        return mapper.mapToResponse(presentationEntity);
    }

    @Override
    public PresentationResponse deleteById(Long presentationId) {
        PresentationEntity presentationEntity = presentationRepository.findById(presentationId)
                .orElseThrow(() -> {
                    throw new NotFoundException(String.format("presentation with id = %s does not exists", presentationId));
                });
        presentationRepository.delete(presentationEntity);
        return mapper.mapToResponse(presentationEntity);
    }
}
