package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.LessonResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.LinkRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.LinkResponse;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.LinkEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.LinkEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.LinkViewMapper;
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
    private final LinkEditMapper linkEditMapper;
    private final LinkViewMapper linkViewMapper;
    private final LinkRepository linkRepository;

    @Override
    public LinkResponse addLink(LinkRequest linkRequest, Long lessonId) {

        LessonEntity lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> {
                    log.error("Lesson with id = {} does not exists", lessonId);
                    throw new NotFoundException(
                            String.format("Lesson with id = %s does not exists", lessonId)
                    );
                });
        LinkEntity linkEntity = linkEditMapper.convertToLink(linkRequest);
        linkEntity.setLessonEntity(lesson);
        log.info(" Link with name : {} has successfully saved to database", linkEntity.getLink());
        return linkViewMapper.convertToLinkResponse(linkRepository.save(linkEntity));
    }

    @Override
    public List<LinkResponse> getAllLinks() {
        List<LinkResponse> linkResponses = new ArrayList<>();
        for (LinkEntity link : linkRepository.findAll()) {
            linkResponses.add(linkViewMapper.convertToLinkResponse(link));
        }
        log.info("Found {} links ", linkResponses.size());
        return linkResponses;
    }

    @Override
    public LinkResponse getLinkById(Long linkId) {
        LinkEntity linkEntity = linkRepository.findById(linkId)
                .orElseThrow(()-> {
                    log.error("Link with id ={} does not exists",linkId);
                    throw new NotFoundException(
                            String.format("link with id = %s does not exists",linkId)
                    );
                });
        return linkViewMapper.convertToLinkResponse(linkEntity);
    }

    @Override
    public LinkResponse updateLink(Long linkId, LinkRequest linkRequest) {
        LinkEntity linkEntity = linkRepository.findById(linkId)
                .orElseThrow(()-> {
                    log.error("Link with id ={} does not exists",linkId);
                    throw new NotFoundException(
                            String.format("link with id = %s does not exists",linkId)
                    );
                });
        linkEditMapper.updateStudent(linkEntity,linkRequest);
        return linkViewMapper.convertToLinkResponse(linkRepository.save(linkEntity));
    }

    @Override
    public void deleteLink(Long linkId) {
        LinkEntity linkEntity = linkRepository.findById(linkId)
                .orElseThrow(()-> {
                    log.error("Link with id ={} does not exists",linkId);
                    throw new NotFoundException(
                            String.format("link with id = %s does not exists",linkId)
                    );
                });
        linkRepository.delete(linkEntity);
    }
}
