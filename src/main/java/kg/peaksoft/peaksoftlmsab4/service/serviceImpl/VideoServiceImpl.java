package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.VideoRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.VideoResponse;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.LessonEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.VideoEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.VideoMapper;
import kg.peaksoft.peaksoftlmsab4.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsab4.repository.VideoRepository;
import kg.peaksoft.peaksoftlmsab4.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final VideoMapper mapper;
    private final LessonRepository lessonRepository;

    @Override
    public VideoResponse create(VideoRequest videoRequest, Long lessonId) {
        LessonEntity lesson = lessonRepository.getById(lessonId);
        VideoEntity videoEntity = videoRepository.save(mapper.mapToEntity(videoRequest,null));
        videoEntity.setLessonEntity(lesson);
        log.info(" Video with name : {} has successfully saved to database", videoEntity.getVideoName());
        return mapper.mapToResponse(videoEntity);
    }

    @Override
    public List<VideoResponse> getAll() {
        log.info("Found {} videos",videoRepository.findAll().size());
        return mapper.mapToResponse(videoRepository.findAll());
    }

    @Override
    public VideoResponse getById(Long videoId) {
        VideoEntity videoEntity = videoRepository.findById(videoId)
                .orElseThrow(()-> {
                    log.error("Video with id = {} does not exists", videoId);
                    throw new NotFoundException(String.format("video with id = %s does not exists",videoId));
                });
        log.info("Found video with id = {} ",videoId);
        return mapper.mapToResponse(videoEntity);
    }

    @Override
    public VideoResponse update(Long videoId, VideoRequest videoRequest) {
        VideoEntity videoEntity = videoRepository.findById(videoId)
                .orElseThrow(()-> {
                    log.error("Video with id ={} does not exists",videoId);
                    throw new NotFoundException(String.format("video with id = %s does not exists",videoId));
                });
        videoEntity.setVideoName(videoRequest.getVideoName());
        videoEntity.setDescription(videoRequest.getDescription());
        videoEntity.setLink(videoRequest.getLink());
        log.info("video with id = {} updated",videoId);
        return mapper.mapToResponse(videoEntity);
    }

    @Override
    public void deleteById(Long videoId) {
        VideoEntity videoEntity = videoRepository.findById(videoId)
                .orElseThrow(()-> {
                    log.error("Video with id = {} does not exists", videoId);
                    throw new NotFoundException(String.format("video with id = %s does not exists",videoId));
                });
        videoRepository.delete(videoEntity);
        log.info("video with id ={} successfully deleted",videoId);
    }
}
