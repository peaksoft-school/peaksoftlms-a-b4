package kg.peaksoft.peaksoftlmsab4.api.controller;

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
public class GroupApi {
    private final GroupService service;

    @PostMapping("/save/{courseId}")
    public GroupResponse create(@RequestBody GroupRequest groupRequest,@PathVariable("courseId") Long id) {
        return service.create(id,groupRequest);
    }

    @PutMapping("/update/{id}")
    public GroupResponse update(@PathVariable Long id, @RequestBody GroupRequest groupRequest) {
        return service.update(id, groupRequest);
    }

    @GetMapping("/get/{id}")
    public GroupResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public GroupResponse deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/all")
    public List<GroupResponse> getAllGroup() {
        return service.getAllGroup();
    }
}
