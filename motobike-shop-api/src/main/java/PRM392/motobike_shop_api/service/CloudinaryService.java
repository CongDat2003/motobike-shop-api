package PRM392.motobike_shop_api.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    
    private final Cloudinary cloudinary;
    
    public String uploadImage(MultipartFile file, String folder) throws IOException {
        Map<String, Object> params = ObjectUtils.asMap(
            "folder", folder,
            "public_id", "product_" + System.currentTimeMillis(),
            "overwrite", true,
            "resource_type", "auto"
        );
        
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
        return (String) uploadResult.get("secure_url");
    }
    
    public String uploadImage(MultipartFile file) throws IOException {
        return uploadImage(file, "motobike-shop");
    }
    
    public void deleteImage(String publicId) throws IOException {
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
}