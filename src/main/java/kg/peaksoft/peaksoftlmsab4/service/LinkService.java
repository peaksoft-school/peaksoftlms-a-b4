package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.LessonResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.LinkRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.LinkResponse;

import java.util.List;

public interface LinkService {
    LinkResponse addLink(LinkRequest linkRequest, Long lessonId);

    List<LinkResponse> getAllLinks();

    LinkResponse getLinkById(Long linkId);

    LinkResponse updateLink(Long linkId, LinkRequest linkRequest);

    void deleteLink(Long linkId);
}
