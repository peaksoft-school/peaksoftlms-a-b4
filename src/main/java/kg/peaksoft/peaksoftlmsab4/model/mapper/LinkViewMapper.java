package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.LinkResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.LinkEntity;
import org.springframework.stereotype.Component;

@Component
public class LinkViewMapper {
    public LinkResponse convertToLinkResponse(LinkEntity link){
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
