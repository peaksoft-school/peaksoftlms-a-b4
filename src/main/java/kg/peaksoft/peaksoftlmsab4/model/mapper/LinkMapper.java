package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.LinkRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.LinkResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.LinkEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LinkMapper {

    public LinkEntity mapToEntity(LinkRequest linkRequest) {
        if (linkRequest == null) {
            return null;
        }
        return LinkEntity.builder()
                .link(linkRequest.getLink())
                .text(linkRequest.getText())
                .build();
    }

    public LinkEntity update(LinkEntity linkEntity, LinkRequest linkRequest) {
        linkEntity.setText(linkRequest.getText());
        linkEntity.setLink(linkRequest.getLink());
        return linkEntity;
    }

    public List<LinkResponse> mapToResponse(List<LinkEntity> links) {
        List<LinkResponse> linkResponses = new ArrayList<>();
        for (LinkEntity linkEntity : links) {
            linkResponses.add(mapToResponse(linkEntity));
        }
        return linkResponses;
    }

    public LinkResponse mapToResponse(LinkEntity link) {
        return LinkResponse.builder()
                .id(link.getId())
                .link(link.getLink())
                .text(link.getText())
                .lessonId(link.getLessonEntity().getId())
                .build();
    }
}
