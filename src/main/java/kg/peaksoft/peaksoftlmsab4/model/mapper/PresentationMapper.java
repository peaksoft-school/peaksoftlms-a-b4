package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.PresentationRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.PresentationResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.PresentationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PresentationMapper {
    public PresentationEntity mapToEntity(PresentationRequest presentationRequest,Long id) {
        if (presentationRequest == null) {
            return null;
        }
        PresentationEntity presentationEntity = new PresentationEntity();
        presentationEntity.setId(id);
        presentationEntity.setPresentationName(presentationRequest.getPresentationName());
        presentationEntity.setDescription(presentationRequest.getDescription());
        presentationEntity.setLink(presentationRequest.getLink());
        return presentationEntity;
    }
    public PresentationResponse mapToResponse(PresentationEntity presentationEntity) {
        if (presentationEntity == null) {
            return null;
        }
        PresentationResponse presentationResponse = new PresentationResponse();
        presentationResponse.setId(presentationEntity.getId());
        presentationResponse.setPresentationName(presentationEntity.getPresentationName());
        presentationResponse.setDescription(presentationEntity.getDescription());
        presentationResponse.setLink(presentationEntity.getLink());
        return presentationResponse;
    }
}
