package kg.peaksoft.peaksoftlmsab4.api.controller;

import kg.peaksoft.peaksoftlmsab4.api.payload.OptionRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.OptionResponse;
import kg.peaksoft.peaksoftlmsab4.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/options")
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@CrossOrigin(origins = "*",maxAge = 3600)
public class OptionApi {
    private final OptionService service;

    @PostMapping("/save")
    public OptionResponse create(@RequestBody OptionRequest optionRequest) {
        return service.create(optionRequest);
    }    @PutMapping("/update/{id}")
    public OptionResponse update(@PathVariable Long id, @RequestBody OptionRequest optionRequest) {
        return service.update(id, optionRequest);
    }

    @PutMapping("/{questionId}/set/{optionId}")
    public OptionResponse findByOptionToQuestion(@PathVariable Long questionId, @PathVariable Long optionId) {
        return service.findByOptionToQuestion(questionId, optionId);
    }

    @GetMapping("/get/{id}")
    public OptionResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public OptionResponse deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/all")
    public List<OptionResponse> findAll() {
        return service.findAll();
    }
}
