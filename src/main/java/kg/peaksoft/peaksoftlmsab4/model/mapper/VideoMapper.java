package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.controller.payload.VideoRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.VideoResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.VideoEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class VideoMapper {

    public VideoEntity mapToEntity(VideoRequest videoRequest) {
        if (videoRequest == null) {
            return null;
        }

        return VideoEntity.builder()
                .videoName(videoRequest.getVideoName())
                .description(videoRequest.getDescription())
                .videoLink(videoRequest.getVideoLink())
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
        if (videoEntity == null) {
            return null;
        }
        return VideoResponse.builder()
                .id(videoEntity.getId())
                .videoName(videoEntity.getVideoName())
                .description(videoEntity.getDescription())
                .videoLink(videoEntity.getVideoLink())
                .lessonId(videoEntity.getLessonEntity().getId())
                .build();
    }
}
