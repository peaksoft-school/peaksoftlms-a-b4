package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.LinkRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.LinkResponse;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.LinkEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.LinkMapper;
import kg.peaksoft.peaksoftlmsab4.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsab4.repository.LinkRepository;
import kg.peaksoft.peaksoftlmsab4.service.LinkService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class LinkServiceImpl implements LinkService {
    private final LessonRepository lessonRepository;
    private final LinkMapper mapper;
    private final LinkRepository linkRepository;

    @Override
    public LinkResponse create(LinkRequest linkRequest, Long lessonId) {

        LessonEntity lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> {
                    log.error("Lesson with id = {} does not exists", lessonId);
                    throw new NotFoundException(
                            String.format("Lesson with id = %s does not exists", lessonId)
                    );
                });
        LinkEntity linkEntity = mapper.mapToEntity(linkRequest,null);
        linkEntity.setLessonEntity(lesson);
        log.info(" Link with name : {} has successfully saved to database", linkEntity.getLink());
        return mapper.mapToResponse(linkRepository.save(linkEntity));
    }

    @Override
    public List<LinkResponse> getAll() {
        log.info("Found {} links ",linkRepository.findAll().size());
        return mapper.mapToResponse(linkRepository.findAll());
    }

    @Override
    public LinkResponse getById(Long linkId) {
        LinkEntity linkEntity = linkRepository.findById(linkId)
                .orElseThrow(()-> {
                    log.error("Link with id ={} does not exists",linkId);
                    throw new NotFoundException(
                            String.format("link with id = %s does not exists",linkId)
                    );
                });
        log.info("Found link with id = {} ",linkId);
        return mapper.mapToResponse(linkEntity);
    }

    @Override
    public LinkResponse update(Long linkId, LinkRequest linkRequest) {
        LinkEntity linkEntity = linkRepository.findById(linkId)
                .orElseThrow(()-> {
                    log.error("Link with id ={} does not exists",linkId);
                    throw new NotFoundException(
                            String.format("link with id = %s does not exists",linkId)
                    );
                });
        mapper.mapToEntity(linkRequest,linkEntity.getId());
        log.info("Link with id = {} updated",linkEntity);
        return mapper.mapToResponse(linkRepository.save(linkEntity));
    }

    @Override
    public void deleteById(Long linkId) {
        LinkEntity linkEntity = linkRepository.findById(linkId)
                .orElseThrow(()-> {
                    log.error("Link with id ={} does not exists",linkId);
                    throw new NotFoundException(
                            String.format("link with id = %s does not exists",linkId)
                    );
                });
        linkRepository.delete(linkEntity);
        log.info("Link with id = {} has successfully deleted", linkId);
    }
}
