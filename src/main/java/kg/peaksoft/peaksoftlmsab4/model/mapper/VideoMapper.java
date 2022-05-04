package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.VideoRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.VideoResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.VideoEntity;
import kg.peaksoft.peaksoftlmsab4.service.serviceImpl.AWSS3Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class VideoMapper {
    private final AWSS3Service awss3Service;

    public VideoEntity mapToEntity(VideoRequest videoRequest) {
        if (videoRequest == null) {
            return null;
        }

        return VideoEntity.builder()
                .videoName(videoRequest.getVideoName())
                .description(videoRequest.getDescription())
                .link(awss3Service.uploadFile(videoRequest.getVideoFile()))
                .build();
    }

    public List<VideoResponse> mapToResponse(List<VideoEntity> videoEntities) {
        List<VideoResponse> videoResponses = new ArrayList<>();
        for (VideoEntity videoEntity : videoEntities) {
            videoResponses.add(mapToResponse(videoEntity));
        }
        return videoResponses;
    }

    public VideoResponse mapToResponse(VideoEntity videoEntity) {
        return VideoResponse.builder()
                .id(videoEntity.getId())
                .videoName(videoEntity.getVideoName())
                .description(videoEntity.getDescription())
                .videoLink(videoEntity.getLink())
                .build();
    }
}
