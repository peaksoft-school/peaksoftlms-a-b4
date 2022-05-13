package kg.peaksoft.peaksoftlmsab4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.api.payload.VideoRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.VideoResponse;
import kg.peaksoft.peaksoftlmsab4.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/videos")
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@AllArgsConstructor
@CrossOrigin(origins = "*",maxAge = 3600)
@Tag(name = "Video", description = "The Video CRUD operations")
public class VideoApi {

    private final VideoService videoService;

    @PostMapping("/{lessonId}")
    @Operation(summary = "Creates new entity: Video", description = "Saves a new video")
    public VideoResponse addVideo(@RequestBody VideoRequest videoRequest, @PathVariable Long lessonId) {
        return videoService.create(videoRequest, lessonId);
    }

    @GetMapping
    @Operation(summary = "Gets all existed videos", description = "Returns all videos in a list ")
    public List<VideoResponse> getAllVideos() {
        return videoService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a single entity by identifier",
            description = "For valid response try integer IDs with value >= 1 ")
    public VideoResponse getVideoById(@PathVariable Long id) {
        return videoService.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates the video ", description = "Updates the details of an endpoint with ID ")
    public VideoResponse updateVideo(@PathVariable Long id,
                                     @RequestBody VideoRequest videoRequest) {
        return videoService.update(id, videoRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes the video ", description = "Deletes video by id ")
    public VideoResponse deleteById(@PathVariable Long id) {
        return videoService.deleteById(id);
    }
}
