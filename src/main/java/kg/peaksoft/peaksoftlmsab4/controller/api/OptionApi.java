package kg.peaksoft.peaksoftlmsab4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.controller.payload.request.OptionRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.OptionResponse;
import kg.peaksoft.peaksoftlmsab4.service.OptionService;
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
@RequestMapping("api/options")
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Option API", description = "Option endpoints")
public class OptionApi {
    private final OptionService service;

    @Operation(summary = "Create option", description = "Save a new option")
    @PostMapping
    public OptionResponse create(@RequestBody OptionRequest request) {
        return service.create(request);
    }

    @Operation(summary = "Update option", description = "Update a existing option")
    @PutMapping("{id}")
    public OptionResponse update(@PathVariable Long id, @RequestBody OptionRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Find option by question", description = "Find option by question")
    @PutMapping("{questionId}/set/{optionId}")
    public OptionResponse findByOptionToQuestion(@PathVariable Long questionId, @PathVariable Long optionId) {
        return service.findByOptionToQuestion(questionId, optionId);
    }

    @Operation(summary = "Get option", description = "Get a existing option")
    @GetMapping("{id}")
    public OptionResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Delete option", description = "Delete a existing option")
    @DeleteMapping("{id}")
    public OptionResponse deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @Operation(summary = "Get all options", description = "Get all existing options")
    @GetMapping
    public List<OptionResponse> findAll() {
        return service.findAll();
    }

}
