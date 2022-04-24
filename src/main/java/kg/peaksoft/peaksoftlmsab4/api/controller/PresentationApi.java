package kg.peaksoft.peaksoftlmsab4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.api.payload.LessonRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.LessonResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.PresentationRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.PresentationResponse;
import kg.peaksoft.peaksoftlmsab4.service.LessonService;
import kg.peaksoft.peaksoftlmsab4.service.PresentationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/presentations")
@PreAuthorize("hasAuthority('ADMIN')")
@AllArgsConstructor
@Tag(name = "Presentation", description = "The Presentation CRUD operations")
public class PresentationApi {

    private final PresentationService presentationService;

    @PostMapping("{lessonId}")
    @Operation(summary = "Creates new entity: Presentation", description = "Saves a new presentation")
    public PresentationResponse addPresentation(
            @RequestBody PresentationRequest presentationRequest
            , @PathVariable Long lessonId) {
        return presentationService.create(presentationRequest, lessonId);
    }

    @GetMapping
    @Operation(summary = "Gets all existed presentations", description = "Returns all presentations in a list ")
    public List<PresentationResponse> getAllPresentations() {
        return presentationService.getAll();
    }

    @GetMapping("{presentationId}")
    @Operation(summary = "Gets a single entity by identifier",
            description = "For valid response try integer IDs with value >= 1 ")
    public PresentationResponse getPresentationsById(@PathVariable Long presentationId) {
        return presentationService.getById(presentationId);
    }


    @PutMapping("{presentationId}")
    @Operation(summary = "Updates the presentation ", description = "Updates the details of an endpoint with ID ")
    public PresentationResponse updatePresentation(@PathVariable Long presentationId,
                                       @RequestBody PresentationRequest presentationRequest) {
        return presentationService.update(presentationId, presentationRequest);
    }

    @DeleteMapping("{presentationId}")
    @Operation(summary = "Deletes the presentation ", description = "Deletes presentation by id ")
    public void deleteById(@PathVariable Long presentationId) {
        presentationService.deleteById(presentationId);
    }
}
