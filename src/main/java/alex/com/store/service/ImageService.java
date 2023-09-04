package alex.com.store.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class ImageService {

    @Value("${image.folder}")
    private String IMAGE_DIR;

    public void saveFile(String fileName, MultipartFile imageFile){
        Path uploadPath = Paths.get(IMAGE_DIR);

        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (InputStream inputStream = imageFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new RuntimeException("Could not save image file: " + fileName, ioe);
        }
    }

    public void deleteImage(String fileName) {
        try {
            Path path = Paths.get(IMAGE_DIR,fileName);
            Files.delete(path);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
