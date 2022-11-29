package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.LinkRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.LinkResponse;

import java.util.List;

public interface LinkService {

    LinkResponse create(LinkRequest linkRequest, Long lessonId);

    List<LinkResponse> getAll();

    LinkResponse getById(Long linkId);

    LinkResponse update(Long linkId, LinkRequest request);

    LinkResponse deleteById(Long linkId);

}
