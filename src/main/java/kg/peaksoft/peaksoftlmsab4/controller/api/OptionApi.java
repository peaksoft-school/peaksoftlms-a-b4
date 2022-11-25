package kg.peaksoft.peaksoftlmsab4.controller.api;

import kg.peaksoft.peaksoftlmsab4.controller.payload.OptionRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.OptionResponse;
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
public class OptionApi {
    private final OptionService service;

    @PostMapping
    public OptionResponse create(@RequestBody OptionRequest request) {
        return service.create(request);
    }

    @PutMapping("{id}")
    public OptionResponse update(@PathVariable Long id, @RequestBody OptionRequest request) {
        return service.update(id, request);
    }

    @PutMapping("{questionId}/set/{optionId}")
    public OptionResponse findByOptionToQuestion(@PathVariable Long questionId, @PathVariable Long optionId) {
        return service.findByOptionToQuestion(questionId, optionId);
    }

    @GetMapping("{id}")
    public OptionResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    public OptionResponse deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping
    public List<OptionResponse> findAll() {
        return service.findAll();
    }

}
