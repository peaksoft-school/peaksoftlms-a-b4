package kg.peaksoft.peaksoftlmsab4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.api.payload.GroupRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.GroupResponse;
import kg.peaksoft.peaksoftlmsab4.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
@PreAuthorize("hasAnyAuthority('ADMIN')")
@Tag(name = "Course", description = "The Group CRUD operations")

public class GroupApi {
    private final GroupService service;

    @Operation(summary = "Creates new entity: Group", description = "Saves a new group")
    @PostMapping("/save/{courseId}")
    public GroupResponse create(@RequestBody GroupRequest groupRequest,@PathVariable("courseId") Long id) {
        return service.create(id,groupRequest);
    }

    @Operation(summary = "Updates the group ", description = "Updates the details of an endpoint with ID ")
    @PutMapping("/update/{id}")
    public GroupResponse update(@PathVariable Long id, @RequestBody GroupRequest groupRequest) {
        return service.update(id, groupRequest);
    }

    @Operation(summary = "Gets a single entity by identifier",
            description = "For valid response try integer IDs with value >= 1 ")
    @GetMapping("/get/{id}")
    public GroupResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Deletes the group ", description = "Deletes groups by id ")
    @DeleteMapping("/delete/{id}")
    public GroupResponse deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @Operation(summary = "Gets all existed groups", description = "Returns all groups in a list ")
    @GetMapping("/all")
    public List<GroupResponse> getAllGroup() {
        return service.getAllGroup();
    }
}
