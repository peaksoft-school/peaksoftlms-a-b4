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
        LessonEntity lesson = lessonRepository.findById(lessonId).
                orElseThrow(()->{
                    log.error("Lesson with id = {} does not exists",lessonId);
                    throw new NotFoundException(
                            String.format("Lesson with id = %s does not exists",lessonId)
                    );
                });
        PresentationEntity presentationEntity = presentationRepository.save(mapper.mapToEntity(request,null));
        presentationEntity.setLessonEntity(lesson);
        log.info("Presentation with name: {} has successfully saved to database",presentationEntity.getPresentationName());
        return mapper.mapToResponse(presentationEntity);
    }

    @Override
    public List<PresentationResponse> getAll() {
        log.info("fount {} presentations",presentationRepository.findAll().size());
        return mapper.mapToResponse(presentationRepository.findAll());
    }

    @Override
    public PresentationResponse getById(Long presentationId) {
        PresentationEntity presentationEntity = presentationRepository.findById(presentationId)
                .orElseThrow(()-> {
                    log.error("Presentation with id = {} does not exists", presentationId);
                    throw new NotFoundException(String.format("presentation with id = %s does not exists",presentationId));
                });
        log.info("Found presentation with id = {} ",presentationId);
        return mapper.mapToResponse(presentationEntity);
    }

    @Override
    public PresentationResponse update(Long presentationId, PresentationRequest presentationRequest) {
        PresentationEntity presentationEntity=presentationRepository.findById(presentationId).
                orElseThrow(()->{
                    log.error("Presentation with id ={} does not exists",presentationId);
                    throw new NotFoundException(
                            String.format("Presentation with id = %s does not exists",presentationId)
                    );
                });
        mapper.mapToEntity(presentationRequest,presentationEntity.getId());
        log.info("Presentation with id = {} updated",presentationId);
        return mapper.mapToResponse(presentationRepository.save(presentationEntity));
    }

    @Override
    public void deleteById(Long presentationId) {
        PresentationEntity presentationEntity = presentationRepository.findById(presentationId)
                .orElseThrow(()-> {
                    log.error("Presentation with id = {} does not exists", presentationId);
                    throw new NotFoundException(String.format("presentation with id = %s does not exists",presentationId));
                });
        presentationRepository.delete(presentationEntity);
        log.info("Presentation with id = {} has successfully deleted", presentationId);
    }
}
