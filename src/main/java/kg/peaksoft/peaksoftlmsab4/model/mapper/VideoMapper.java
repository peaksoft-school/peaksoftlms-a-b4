package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.VideoRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.VideoResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.VideoEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VideoMapper {
    public VideoEntity mapToEntity(VideoRequest videoRequest,Long id) {
        if (videoRequest == null) {
            return null;
        }
        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setId(id);
        videoEntity.setVideoName(videoRequest.getVideoName());
        videoEntity.setDescription(videoRequest.getDescription());
        videoEntity.setLink(videoRequest.getLink());
        return videoEntity;
    }
    public VideoResponse mapToResponse(VideoEntity videoEntity) {
        if (videoEntity == null) {
            return null;
        }
        VideoResponse videoResponse = new VideoResponse();
        videoResponse.setId(videoEntity.getId());
        videoResponse.setVideoName(videoEntity.getVideoName());
        videoResponse.setDescription(videoEntity.getDescription());
        videoResponse.setLink(videoEntity.getLink());
        return videoResponse;
    }
}
