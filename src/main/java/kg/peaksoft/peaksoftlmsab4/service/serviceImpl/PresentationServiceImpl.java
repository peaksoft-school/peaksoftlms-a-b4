package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.PresentationRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.PresentationResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.PresentationEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.PresentationMapper;
import kg.peaksoft.peaksoftlmsab4.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsab4.repository.PresentationRepository;
import kg.peaksoft.peaksoftlmsab4.service.PresentationService;
import kg.peaksoft.peaksoftlmsab4.validation.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.validation.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
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
        PresentationEntity presentation = presentationRepository.findById(presentationId).orElseThrow(() -> {
            throw new NotFoundException(String.format("presentation with id = %s does not exists", presentationId));
        });
        return mapper.mapToResponse(presentation);
    }

    @Override
    public PresentationResponse update(Long presentationId, PresentationRequest request) {
        PresentationEntity presentation = presentationRepository.findById(presentationId).orElseThrow(() -> {
            throw new NotFoundException(String.format("Presentation with id = %s does not exists", presentationId));
        });
        mapper.update(presentation, request);
        presentationRepository.save(presentation);
        return mapper.mapToResponse(presentation);
    }

    @Override
    public PresentationResponse deleteById(Long presentationId) {
        PresentationEntity presentation = presentationRepository.findById(presentationId).orElseThrow(() -> {
            throw new NotFoundException(String.format("presentation with id = %s does not exists", presentationId));
        });
        presentationRepository.deleteById(presentationId);
        return mapper.mapToResponse(presentation);
    }

}
