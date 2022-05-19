package kg.peaksoft.peaksoftlmsab4.api.controller;

import kg.peaksoft.peaksoftlmsab4.api.payload.ResultRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.ResultResponse;
import kg.peaksoft.peaksoftlmsab4.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resulting")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ResultApi {
    private final ResultService service;

    @PostMapping("/save")
    public ResultResponse createResult(@RequestBody ResultRequest resultRequest){
        return service.createResult(resultRequest);
    }

    @GetMapping("/all")
    public List<ResultResponse> getAll(){
        return service.getAll();
    }
}
