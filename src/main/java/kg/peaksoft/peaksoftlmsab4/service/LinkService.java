package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.LinkRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.LinkResponse;

import java.util.List;

public interface LinkService {
    LinkResponse create(LinkRequest linkRequest, Long lessonId);

    List<LinkResponse> getAll();

    LinkResponse getById(Long linkId);

    LinkResponse update(Long linkId, LinkRequest linkRequest);

    LinkResponse deleteById(Long linkId);
}
