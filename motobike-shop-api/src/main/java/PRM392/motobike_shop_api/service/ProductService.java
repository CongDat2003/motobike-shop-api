package PRM392.motobike_shop_api.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import PRM392.motobike_shop_api.entity.Product;
import PRM392.motobike_shop_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final CloudinaryService cloudinaryService;
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    
    public List<String> getAllCategories() {
        return productRepository.findDistinctCategories();
    }
    
    public List<Product> searchProducts(String keyword) {
        return productRepository.searchProducts(keyword);
    }
    
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product updateProduct(Long id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        
        if (existingProduct.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        
        Product updatedProduct = existingProduct.get();
        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setImageUrl(product.getImageUrl());
        updatedProduct.setCategory(product.getCategory());
        updatedProduct.setStock(product.getStock());
        updatedProduct.setBrand(product.getBrand());
        updatedProduct.setModel(product.getModel());
        updatedProduct.setSpecifications(product.getSpecifications());
        
        return productRepository.save(updatedProduct);
    }
    
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        
        productRepository.deleteById(id);
    }

    public String uploadProductImage(Long productId, MultipartFile file) throws IOException {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            throw new RuntimeException("Product not found");
        }

        // Upload to Cloudinary
        String imageUrl = cloudinaryService.uploadImage(file, "motobike-shop/products");

        return imageUrl;
    }
    
    public String uploadSecondaryImage(Long productId, MultipartFile file, int imageNumber) throws IOException {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            throw new RuntimeException("Product not found");
        }

        // Upload to Cloudinary
        String imageUrl = cloudinaryService.uploadImage(file, "motobike-shop/products");

        // Update product with secondary image URL
        Product product = productOpt.get();
        switch (imageNumber) {
            case 1:
                product.setSecondaryImage1(imageUrl);
                break;
            case 2:
                product.setSecondaryImage2(imageUrl);
                break;
            case 3:
                product.setSecondaryImage3(imageUrl);
                break;
        }
        productRepository.save(product);

        return imageUrl;
    }

    public Product updateProductImageUrl(Long id, String imageUrl) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isEmpty()) {
            throw new RuntimeException("Product not found");
        }

        Product product = productOpt.get();
        product.setImageUrl(imageUrl);
        return productRepository.save(product);
    }
}









