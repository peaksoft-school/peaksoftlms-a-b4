package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.LinkRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.LinkResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.LinkEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LinkMapper {

    public LinkEntity mapToEntity(LinkRequest linkRequest,Long id) {
        if (linkRequest == null) {
            return null;
        }
        LinkEntity linkEntity = new LinkEntity();
        linkEntity.setId(id);
        linkEntity.setLink(linkRequest.getLink());
        linkEntity.setText(linkRequest.getText());
        return linkEntity;
    }
    public LinkResponse mapToResponse(LinkEntity link){
        if(link==null){
            return null;
        }
        LinkResponse linkResponse = new LinkResponse();
        linkResponse.setId(link.getId());
        linkResponse.setLink(link.getLink());
        linkResponse.setText(link.getText());
        return linkResponse;
    }
}
