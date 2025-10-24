package PRM392.motobike_shop_api.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductFormDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String category;
    private Integer stock;
    private String brand;
    private String model;
    private String specifications;
    
    // Image files
    private MultipartFile imageFile;
    private MultipartFile secondaryImage1;
    private MultipartFile secondaryImage2;
    private MultipartFile secondaryImage3;
    
    // Current image URLs (for editing)
    private String imageUrl;
    private String currentSecondaryImage1;
    private String currentSecondaryImage2;
    private String currentSecondaryImage3;
}

