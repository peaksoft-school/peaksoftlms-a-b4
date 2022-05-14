package kg.peaksoft.peaksoftlmsab4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsab4.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

@RestController
@RequestMapping("/api/file")
@AllArgsConstructor
@PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Upload File", description = "The Upload File s3 operations")
public class UploadFileApi {

    private final FileService fileService;

    @Operation(summary = "Save file to s3", description = "Save file to aws s3 repository and get link for this file")
    @PostMapping
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        String url = fileService.uploadFile(file);
        Map<String, String> response = new HashMap<>();
        response.put("url", url);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Download file", description = "Download file from aws s3 repository by file name")
    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable("filename") String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", MediaType.ALL_VALUE);
        headers.add("Content-Disposition", "attachment; filename=" + filename);
        byte[] bytes = fileService.downloadFile(filename);
        return ResponseEntity.status(HTTP_OK).headers(headers).body(bytes);
    }

    @Operation(summary = "Delete file", description = "Delete file from aws s3 by file name")
    @DeleteMapping("/{filename}")
    public String deleteFile(@PathVariable("filename") String filename) {
        return fileService.deleteFile(filename);
    }

}
