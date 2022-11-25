package kg.peaksoft.peaksoftlmsab4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.controller.payload.PresentationRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.PresentationResponse;
import kg.peaksoft.peaksoftlmsab4.service.PresentationService;
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
@RequestMapping("api/presentations")
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Presentation API", description = "Presentation endpoints")
public class PresentationApi {

    private final PresentationService presentationService;

    @PostMapping("/{lessonId}")
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

    @GetMapping("/{id}")
    @Operation(summary = "Gets a single entity by identifier",
            description = "For valid response try integer IDs with value >= 1 ")
    public PresentationResponse getPresentationsById(@PathVariable Long id) {
        return presentationService.getById(id);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Updates the presentation ", description = "Updates the details of an endpoint with ID ")
    public PresentationResponse updatePresentation(@PathVariable Long id,
                                                   @RequestBody PresentationRequest presentationRequest) {
        return presentationService.update(id, presentationRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes the presentation ", description = "Deletes presentation by id ")
    public PresentationResponse deleteById(@PathVariable Long id) {
        return presentationService.deleteById(id);
    }
}
