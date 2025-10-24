package PRM392.motobike_shop_api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LocalImageService {
    
    private static final String UPLOAD_DIR = "src/main/resources/static/images/uploads/";
    private static final String BASE_URL = "/images/uploads/";
    
    /**
     * Upload a single image and return the URL
     */
    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        
        // Create upload directory if it doesn't exist
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        // Generate unique filename
        String originalFilename = file.getOriginalFilename();
        String extension = getFileExtension(originalFilename);
        String uniqueFilename = UUID.randomUUID().toString() + extension;
        
        // Save file
        Path filePath = uploadPath.resolve(uniqueFilename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        
        // Return URL path
        return BASE_URL + uniqueFilename;
    }
    
    /**
     * Upload multiple images and return list of URLs
     */
    public List<String> uploadImages(List<MultipartFile> files) throws IOException {
        List<String> urls = new ArrayList<>();
        
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String url = uploadImage(file);
                urls.add(url);
            }
        }
        
        return urls;
    }
    
    /**
     * Delete an image file
     */
    public boolean deleteImage(String imageUrl) {
        try {
            if (imageUrl != null && imageUrl.startsWith(BASE_URL)) {
                String filename = imageUrl.substring(BASE_URL.length());
                Path filePath = Paths.get(UPLOAD_DIR + filename);
                return Files.deleteIfExists(filePath);
            }
        } catch (IOException e) {
            System.err.println("Error deleting image: " + e.getMessage());
        }
        return false;
    }
    
    /**
     * Get file extension from filename
     */
    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return ".jpg"; // default extension
        }
        return filename.substring(filename.lastIndexOf("."));
    }
    
    /**
     * Validate file type
     */
    public boolean isValidImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && (
            contentType.equals("image/jpeg") ||
            contentType.equals("image/png") ||
            contentType.equals("image/gif") ||
            contentType.equals("image/webp")
        );
    }
    
    /**
     * Get file size in MB
     */
    public double getFileSizeInMB(MultipartFile file) {
        return file.getSize() / (1024.0 * 1024.0);
    }
}

