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
//@PreAuthorize("hasAuthority('INSTRUCTOR')")
public class OptionApi {
    private final OptionService service;

    @PostMapping("/save")
    public OptionResponse create(@RequestBody OptionRequest optionRequest) {
        return service.create(optionRequest);
    }

    @PutMapping("/update/{id}")
    public OptionResponse update(@PathVariable Long id, @RequestBody OptionRequest optionRequest) {
        return service.update(id, optionRequest);
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
