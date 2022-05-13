package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.api.payload.VideoRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.VideoResponse;

import java.util.List;

public interface VideoService {
    VideoResponse create(VideoRequest videoRequest, Long lessonId);

    List<VideoResponse> getAll();

    VideoResponse getById(Long videoId);

    VideoResponse update(Long videoId, VideoRequest videoRequest);

    VideoResponse deleteById(Long videoId);
}
