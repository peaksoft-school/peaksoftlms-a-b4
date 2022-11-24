package kg.peaksoft.peaksoftlmsab4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.controller.payload.GroupRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.GroupResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.PaginationResponse;
import kg.peaksoft.peaksoftlmsab4.controller.payload.StudentResponse;
import kg.peaksoft.peaksoftlmsab4.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Group", description = "The Group CRUD operations")
public class GroupApi {
    private final GroupService service;

    @Operation(summary = "Creates new entity: Group ", description = "Saves a new group")
    @PostMapping
    public GroupResponse saveGroup(@RequestBody GroupRequest groupRequest) {
        return service.saveGroup(groupRequest);
    }

    @Operation(summary = "Updates the group ", description = "Updates the details of an endpoint with ID ")
    @PutMapping("/{id}")
    public GroupResponse update(@PathVariable Long id, @RequestBody GroupRequest groupRequest) {
        return service.update(id, groupRequest);
    }

    @Operation(summary = "Gets a group by identifier",
            description = "For valid response try integer IDs with value >= 1 ")
    @GetMapping("/{id}")
    public GroupResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Deletes the group ", description = "Deletes groups by id ")
    @DeleteMapping("/{id}")
    public GroupResponse deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @Operation(summary = "Gets all existed groups", description = "Returns all groups in a list ")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR','ADMIN')")
    @GetMapping
    public List<GroupResponse> getAllGroup() {
        return service.getAllGroup();
    }

    @Operation(summary = "Assign group to course", description = "Add a group to course")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR','ADMIN')")
    @PutMapping("/accept-to-course")
    public GroupResponse setGroupToCourse(@RequestParam Long groupId, @RequestParam Long courseId) {
        return service.setGroupToCourse(groupId, courseId);
    }

    @Operation(summary = "Get students by group id",
            description = "Get all students in this group")
    @GetMapping("/students/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<StudentResponse> getAllStudentByGroupId(@PathVariable Long id) {
        return service.getAllStudentsByGroupId(id);
    }

    @GetMapping("/pagination")
    public PaginationResponse<GroupResponse> getGroupPagination(@RequestParam int page, @RequestParam int size) {
        return service.getGroupPagination(page - 1, size);
    }
}
