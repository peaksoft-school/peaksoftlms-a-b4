package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.LinkRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.LinkResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.LinkEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.LinkMapper;
import kg.peaksoft.peaksoftlmsab4.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsab4.repository.LinkRepository;
import kg.peaksoft.peaksoftlmsab4.service.LinkService;
import kg.peaksoft.peaksoftlmsab4.validation.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.validation.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final LessonRepository lessonRepository;
    private final LinkMapper mapper;
    private final LinkRepository linkRepository;

    @Override
    public LinkResponse create(LinkRequest linkRequest, Long lessonId) {
        LessonEntity lesson = lessonRepository.findById(lessonId).orElseThrow(() -> {
            log.error("Lesson with id = {} does not exists", lessonId);
            throw new NotFoundException(String.format("Lesson with id = %s does not exists", lessonId));
        });
        if (lesson.getLinkEntity() == null) {
            LinkEntity linkEntity = mapper.mapToEntity(linkRequest);
            linkEntity.setLessonEntity(lesson);
            lesson.setLinkEntity(linkEntity);
            log.info(" Link with name : {} has successfully saved to database", linkEntity.getLink());
            return mapper.mapToResponse(linkRepository.save(linkEntity));
        } else {
            throw new BadRequestException("In this lesson link already exists");
        }
    }

    @Override
    public List<LinkResponse> getAll() {
        log.info("Found {} links ", linkRepository.findAll().size());
        return mapper.mapToResponse(linkRepository.findAll());
    }

    @Override
    public LinkResponse getById(Long linkId) {
        LinkEntity linkEntity = linkRepository.findById(linkId).orElseThrow(() -> {
            log.error("Link with id ={} does not exists", linkId);
            throw new NotFoundException(String.format("link with id = %s does not exists", linkId));
        });
        return mapper.mapToResponse(linkEntity);
    }

    @Override
    public LinkResponse update(Long linkId, LinkRequest request) {
        LinkEntity linkEntity = linkRepository.findById(linkId).orElseThrow(() -> {
            log.error("Link with id ={} does not exists", linkId);
            throw new NotFoundException(String.format("link with id = %s does not exists", linkId));
        });
        mapper.update(linkEntity, request);
        return mapper.mapToResponse(linkRepository.save(linkEntity));
    }

    @Override
    public LinkResponse deleteById(Long linkId) {
        LinkEntity linkEntity = linkRepository.findById(linkId).orElseThrow(() -> {
            log.error("Link with id ={} does not exists", linkId);
            throw new NotFoundException(String.format("link with id = %s does not exists", linkId));
        });
        linkRepository.deleteById(linkId);
        return mapper.mapToResponse(linkEntity);
    }

}
