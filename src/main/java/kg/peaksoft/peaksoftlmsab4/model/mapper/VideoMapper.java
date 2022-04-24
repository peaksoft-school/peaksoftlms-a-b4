package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.LessonResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.VideoRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.VideoResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.VideoEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class VideoMapper {
    public VideoEntity mapToEntity(VideoRequest videoRequest,Long id) {
        if (videoRequest == null) {
            return null;
        }
        return VideoEntity.builder()
                .id(id)
                .link(videoRequest.getLink())
                .videoName(videoRequest.getVideoName())
                .description(videoRequest.getDescription())
                .build();
    }

    public List<VideoResponse> mapToResponse(List<VideoEntity> videoEntities){
        List<VideoResponse> videoResponses = new ArrayList<>();
        for (VideoEntity videoEntity:videoEntities) {
            videoResponses.add(mapToResponse(videoEntity));
        }
        return videoResponses;
    }

    public VideoResponse mapToResponse(VideoEntity videoEntity) {
        return VideoResponse.builder()
                .id(videoEntity.getId())
                .videoName(videoEntity.getVideoName())
                .description(videoEntity.getDescription())
                .link(videoEntity.getLink())
                .build();
    }
}
