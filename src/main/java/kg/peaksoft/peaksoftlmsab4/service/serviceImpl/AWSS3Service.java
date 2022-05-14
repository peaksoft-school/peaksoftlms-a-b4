package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import kg.peaksoft.peaksoftlmsab4.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
@AllArgsConstructor
public class AWSS3Service implements FileService {

    private final AmazonS3Client awsS3Client;

    @Override
    public String uploadFile(MultipartFile file) {
        String key = file.getOriginalFilename();

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

    @Override
    public byte[] downloadFile(String fileName) {
        S3Object object = awsS3Client.getObject("peaksoft-lms-a", fileName);
        S3ObjectInputStream objectContent = object.getObjectContent();
        try {
            return IOUtils.toByteArray(objectContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteFile(String fileName) {
        awsS3Client.deleteObject("peaksoft-lms-a", fileName);
        return "File deleted";
    }
}
