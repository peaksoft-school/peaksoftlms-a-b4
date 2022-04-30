package kg.peaksoft.peaksoftlmsab4.api.controller;

import kg.peaksoft.peaksoftlmsab4.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/file")
public class UploadFileApi {

    @Autowired
    private FileService fileService;

    @PostMapping
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        String publicURL = fileService.uploadFile(file);
        Map<String, String> response = new HashMap<>();
        response.put("publicURL", publicURL);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
