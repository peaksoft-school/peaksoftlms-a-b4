package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import kg.peaksoft.peaksoftlmsab4.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AWSS3Service implements FileService {

    private final AmazonS3Client awsS3Client;

    @Override
    public String uploadFile(MultipartFile file) {
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = UUID.randomUUID().toString() + "." + extension;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            awsS3Client.putObject("peaksoft-lms-a", key, file.getInputStream(), metadata);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while uploading the file");
        }
        awsS3Client.setObjectAcl("peaksoft-lms-a", key, CannedAccessControlList.PublicRead);
        return awsS3Client.getResourceUrl("peaksoft-lms-a", key);
    }
}
