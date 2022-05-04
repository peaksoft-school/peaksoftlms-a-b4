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

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final VideoMapper mapper;
    private final LessonRepository lessonRepository;
    private final AWSS3Service awss3Service;

    @Override
    public VideoResponse create(VideoRequest videoRequest, Long lessonId) {
        LessonEntity lesson = lessonRepository.getById(lessonId);

        VideoEntity videoEntity = mapper.mapToEntity(videoRequest);
        videoEntity.setLessonEntity(lesson);
        VideoEntity savedVideoEntity = videoRepository.save(videoEntity);
        log.info(" Video with name : {} has successfully saved to database", videoEntity.getVideoName());
        return mapper.mapToResponse(savedVideoEntity);
    }

    @Override
    public List<VideoResponse> getAll() {
        log.info("Fount {} videos", videoRepository.findAll().size());
        return mapper.mapToResponse(videoRepository.findAll());
    }

    @Override
    public VideoResponse getById(Long videoId) {
        VideoEntity videoEntity = videoRepository.findById(videoId)
                .orElseThrow(() -> {
                    throw new NotFoundException(String.format("video with id = %s does not exists", videoId));
                });
        return mapper.mapToResponse(videoEntity);
    }

    @Override
    public VideoResponse update(Long videoId, VideoRequest videoRequest) {
        VideoEntity videoEntity = videoRepository.findById(videoId)
                .orElseThrow(() -> {
                    throw new NotFoundException(String.format("video with id = %s does not exists", videoId));
                });
        videoEntity.setVideoName(videoRequest.getVideoName());
        videoEntity.setDescription(videoRequest.getDescription());
        videoEntity.setLink(awss3Service.uploadFile(videoRequest.getVideoFile()));
        videoRepository.save(videoEntity);
        log.info("video with id = {} updated", videoId);
        return mapper.mapToResponse(videoEntity);
    }

    @Override
    public VideoResponse deleteById(Long videoId) {
        VideoEntity videoEntity = videoRepository.findById(videoId)
                .orElseThrow(() -> {
                    throw new NotFoundException(String.format("video with id = %s does not exists", videoId));
                });
        awss3Service.deleteFile(videoEntity.getVideoName());
        videoRepository.delete(videoEntity);
        log.info("video with id ={} successfully deleted", videoId);
        return mapper.mapToResponse(videoEntity);
    }
}
