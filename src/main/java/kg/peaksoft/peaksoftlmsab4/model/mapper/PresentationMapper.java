package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.PresentationRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.PresentationResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.PresentationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class PresentationMapper {

    public PresentationEntity mapToEntity(PresentationRequest presentationRequest) {
        if (presentationRequest == null) {
            return null;
        }
        return PresentationEntity.builder()
                .presentationName(presentationRequest.getPresentationName())
                .description(presentationRequest.getDescription())
                .presentationLink(presentationRequest.getPresentationLink())
                .build();
    }

    public PresentationEntity update(PresentationEntity presentationEntity, PresentationRequest presentationRequest) {
        presentationEntity.setPresentationName(presentationRequest.getPresentationName());
        presentationEntity.setDescription(presentationRequest.getDescription());
        presentationEntity.setPresentationLink(presentationRequest.getPresentationLink());
        return presentationEntity;
    }

    public List<PresentationResponse> mapToResponse(List<PresentationEntity> presentationEntities) {
        List<PresentationResponse> presentationResponses = new ArrayList<>();
        for (PresentationEntity presentationEntity : presentationEntities) {
            presentationResponses.add(mapToResponse(presentationEntity));
        }
        return presentationResponses;
    }

    public PresentationResponse mapToResponse(PresentationEntity presentationEntity) {
        if (presentationEntity == null) {
            return null;
        }
        return PresentationResponse.builder()
                .id(presentationEntity.getId())
                .presentationName(presentationEntity.getPresentationName())
                .description(presentationEntity.getDescription())
                .presentationLink(presentationEntity.getPresentationLink())
                .lessonId(presentationEntity.getLessonEntity().getId())
                .build();
    }

}
