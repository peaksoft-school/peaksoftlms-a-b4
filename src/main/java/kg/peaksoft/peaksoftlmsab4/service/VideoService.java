package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.VideoRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.VideoResponse;

import java.util.List;

public interface VideoService {
    VideoResponse create(VideoRequest videoRequest, Long lessonId);

    List<VideoResponse> getAll();

    VideoResponse getById(Long videoId);

    VideoResponse update(Long videoId, VideoRequest videoRequest);

    VideoResponse deleteById(Long videoId);

    VideoResponse getVideoByLessonId(Long id);
}
