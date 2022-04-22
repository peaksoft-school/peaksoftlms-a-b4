package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.LessonRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.LinkRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.LinkEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LinkEditMapper {

    public LinkEntity convertToLink(LinkRequest linkRequest) {
        if (linkRequest == null) {
            return null;
        }

        LinkEntity linkEntity = new LinkEntity();
        linkEntity.setLink(linkRequest.getLink());
        linkEntity.setText(linkRequest.getText());
        return linkEntity;
    }

    public void updateStudent(LinkEntity linkEntity, LinkRequest linkRequest) {
        linkEntity.setLink(linkRequest.getLink());
        linkEntity.setText(linkRequest.getText());
    }

}
