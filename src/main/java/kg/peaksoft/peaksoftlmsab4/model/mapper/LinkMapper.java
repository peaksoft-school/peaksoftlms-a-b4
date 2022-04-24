package kg.peaksoft.peaksoftlmsab4.model.mapper;

import io.swagger.v3.oas.models.links.Link;
import kg.peaksoft.peaksoftlmsab4.api.payload.LessonResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.LinkRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.LinkResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.LinkEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class LinkMapper {

    public LinkEntity mapToEntity(LinkRequest linkRequest,Long id) {
        if (linkRequest == null) {
            return null;
        }
        return LinkEntity.builder()
                .id(id)
                .link(linkRequest.getLink())
                .text(linkRequest.getText())
                .build();
    }

    public List<LinkResponse> mapToResponse(List<LinkEntity> links){
        List<LinkResponse> linkResponses = new ArrayList<>();
        for (LinkEntity linkEntity:links) {
            linkResponses.add(mapToResponse(linkEntity));
        }
        return linkResponses;
    }

    public LinkResponse mapToResponse(LinkEntity link){
        return LinkResponse.builder()
                .id(link.getId())
                .link(link.getLink())
                .text(link.getText())
                .build();
    }
}
