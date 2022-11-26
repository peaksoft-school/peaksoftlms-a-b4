package kg.peaksoft.peaksoftlmsab4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.controller.payload.request.VideoRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.VideoResponse;
import kg.peaksoft.peaksoftlmsab4.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/videos")
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Video API", description = "Video endpoints")
public class VideoApi {

    private final VideoService videoService;

    @Operation(summary = "Creates new entity: Video", description = "Saves a new video")
    @PostMapping("{lessonId}")
    public VideoResponse addVideo(@RequestBody VideoRequest request, @PathVariable Long lessonId) {
        return videoService.create(request, lessonId);
    }

    @Operation(summary = "Gets all existed videos", description = "Returns all videos in a list ")
    @GetMapping
    public List<VideoResponse> getAllVideos() {
        return videoService.getAll();
    }

    @Operation(summary = "Gets a single entity by identifier", description = "For valid response try integer IDs with value >= 1 ")
    @GetMapping("{id}")
    public VideoResponse getVideoById(@PathVariable Long id) {
        return videoService.getById(id);
    }

    @Operation(summary = "Updates the video ", description = "Updates the details of an endpoint with ID ")
    @PutMapping("{id}")
    public VideoResponse updateVideo(@PathVariable Long id, @RequestBody VideoRequest request) {
        return videoService.update(id, request);
    }

    @Operation(summary = "Deletes the video ", description = "Deletes video by id ")
    @DeleteMapping("{id}")
    public VideoResponse deleteById(@PathVariable Long id) {
        return videoService.deleteById(id);
    }

    @Operation(summary = "Gets a single video by lesson id", description = "For valid response try integer IDs with value >= 1 ")
    @GetMapping("video-lesson/{id}")
    public VideoResponse getVideoByLessonId(@PathVariable Long id) {
        return videoService.getVideoByLessonId(id);
    }

}
