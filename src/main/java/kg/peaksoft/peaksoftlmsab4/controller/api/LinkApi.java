package kg.peaksoft.peaksoftlmsab4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.controller.payload.LinkRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.LinkResponse;
import kg.peaksoft.peaksoftlmsab4.service.LinkService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/links")
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@AllArgsConstructor
@CrossOrigin(origins = "*",maxAge = 3600)
@Tag(name = "Link", description = "The Link CRUD operations")
public class LinkApi {

    private final LinkService linkService;

    @PostMapping("/{lessonId}")
    @Operation(summary = "Creates new entity: Link", description = "Saves a new link")
    public LinkResponse addLink(@RequestBody LinkRequest linkRequest, @PathVariable Long lessonId) {
        return linkService.create(linkRequest, lessonId);
    }

    @GetMapping
    @Operation(summary = "Gets all existed links", description = "Returns all links in a list ")
    public List<LinkResponse> getAllLinks() {
        return linkService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a single entity by identifier",
            description = "For valid response try integer IDs with value >= 1 ")
    public LinkResponse getLinkById(@PathVariable Long id) {
        return linkService.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates the link ", description = "Updates the details of an endpoint with ID ")
    public LinkResponse updateLink(@PathVariable Long id,
                                   @RequestBody LinkRequest linkRequest) {
        return linkService.update(id, linkRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes the link ", description = "Deletes link by id ")
    public LinkResponse deleteById(@PathVariable Long id) {
        return linkService.deleteById(id);
    }
}
