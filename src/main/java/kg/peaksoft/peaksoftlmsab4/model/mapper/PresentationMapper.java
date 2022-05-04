package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.PresentationRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.PresentationResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.PresentationEntity;
import kg.peaksoft.peaksoftlmsab4.service.serviceImpl.AWSS3Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class PresentationMapper {
    private final AWSS3Service awss3Service;

    public PresentationEntity mapToEntity(PresentationRequest presentationRequest, Long id) {
        if (presentationRequest == null) {
            return null;
        }
        return PresentationEntity.builder()
                .id(id)
                .presentationName(presentationRequest.getPresentationName())
                .description(presentationRequest.getDescription())
                .link(awss3Service.uploadFile(presentationRequest.getPresentationFile()))
                .build();
    }

    public List<PresentationResponse> mapToResponse(List<PresentationEntity> presentationEntities) {
        List<PresentationResponse> presentationResponses = new ArrayList<>();
        for (PresentationEntity presentationEntity : presentationEntities) {
            presentationResponses.add(mapToResponse(presentationEntity));
        }
        return presentationResponses;
    }

    public PresentationResponse mapToResponse(PresentationEntity presentationEntity) {
        return PresentationResponse.builder()
                .id(presentationEntity.getId())
                .presentationName(presentationEntity.getPresentationName())
                .description(presentationEntity.getDescription())
                .presentationLink(presentationEntity.getLink())
                .build();
    }
}
