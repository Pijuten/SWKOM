package at.fhtw.ocrservice.services.ocr;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class MinoIOFileDownloader  {

    private final MinioClient minioClient;
    @Autowired
    public MinoIOFileDownloader(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public File download(String fileName) {
        String bucketName = "paperless"; // replace with your bucket name
        try {
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build());

            File file = new File(fileName);
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = stream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
            stream.close();
            return file;
        } catch (MinioException | IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately in production code
            return null;
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
