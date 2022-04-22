package kg.peaksoft.peaksoftlmsab4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.api.payload.LessonRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.LessonResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.LinkRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.LinkResponse;
import kg.peaksoft.peaksoftlmsab4.service.LessonService;
import kg.peaksoft.peaksoftlmsab4.service.LinkService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/links")
@PreAuthorize("hasAuthority('ADMIN')")
@AllArgsConstructor
@Tag(name = "Link", description = "The Link CRUD operations")
public class LinkApi {

    private final LinkService linkService;

    @PostMapping("{lessonId}")
    @Operation(summary = "Creates new entity: Link", description = "Saves a new link")
    public LinkResponse addLink(@RequestBody LinkRequest linkRequest, @PathVariable Long lessonId) {
        return linkService.addLink(linkRequest, lessonId);
    }

    @GetMapping
    @Operation(summary = "Gets all existed links", description = "Returns all links in a list ")
    public List<LinkResponse> getAllLinks() {
        return linkService.getAllLinks();
    }

    @GetMapping("{linkId}")
    @Operation(summary = "Gets a single entity by identifier",
            description = "For valid response try integer IDs with value >= 1 ")
    public LinkResponse getLinkById(@PathVariable Long linkId) {
        return linkService.getLinkById(linkId);
    }

    @PutMapping("{linkId}")
    @Operation(summary = "Updates the link ", description = "Updates the details of an endpoint with ID ")
    public LinkResponse updateLink(@PathVariable Long linkId,
                                       @RequestBody LinkRequest linkRequest) {
        return linkService.updateLink(linkId, linkRequest);
    }

    @DeleteMapping("{linkId}")
    @Operation(summary = "Deletes the link ", description = "Deletes link by id ")
    public void deleteById(@PathVariable Long linkId) {
        linkService.deleteLink(linkId);
    }
}
