package PRM392.motobike_shop_api.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import PRM392.motobike_shop_api.dto.ProductFormDto;
import PRM392.motobike_shop_api.entity.Order;
import PRM392.motobike_shop_api.entity.Product;
import PRM392.motobike_shop_api.repository.ProductRepository;
import PRM392.motobike_shop_api.service.LocalImageService;
import PRM392.motobike_shop_api.service.OrderService;
import PRM392.motobike_shop_api.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;
    private final OrderService orderService;
    private final ProductRepository productRepository;
    private final LocalImageService localImageService;

    @GetMapping
    public String adminDashboard(Model model) {
        List<Product> products = productService.getAllProducts();
        List<Order> orders = orderService.getAllOrders();

        // Product statistics
        model.addAttribute("products", products);
        model.addAttribute("totalProducts", products.size());
        model.addAttribute("totalStock", products.stream().mapToInt(Product::getStock).sum());
        model.addAttribute("outOfStockProducts", products.stream().mapToInt(p -> p.getStock() == 0 ? 1 : 0).sum());

        // Order statistics
        model.addAttribute("totalOrders", orders.size());
        model.addAttribute("pendingOrders", orderService.getOrderCountByStatus(Order.OrderStatus.PENDING));
        model.addAttribute("confirmedOrders", orderService.getOrderCountByStatus(Order.OrderStatus.CONFIRMED));
        model.addAttribute("deliveredOrders", orderService.getOrderCountByStatus(Order.OrderStatus.DELIVERED));

        // Revenue statistics (last 30 days)
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("totalRevenue", orderService.getTotalRevenueByStatusAndDateRange(Order.OrderStatus.DELIVERED, thirtyDaysAgo, now));

        // Recent orders
        model.addAttribute("recentOrders", orders.stream().limit(5).toList());

        return "admin/dashboard";
    }

    @GetMapping("/products")
    public String products(@RequestParam(value = "search", required = false) String search,
                           @RequestParam(value = "category", required = false) String category,
                           @RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "size", defaultValue = "10") int size,
                           Model model) {
        List<Product> products;

        if (search != null && !search.trim().isEmpty()) {
            products = productService.searchProducts(search.trim());
        } else if (category != null && !category.trim().isEmpty()) {
            products = productService.getProductsByCategory(category.trim());
        } else {
            products = productService.getAllProducts();
        }

        model.addAttribute("products", products);
        model.addAttribute("search", search);
        model.addAttribute("category", category);
        model.addAttribute("categories", productService.getAllCategories());
        return "admin/products";
    }

    @GetMapping("/products/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new ProductFormDto());
        return "admin/product-form";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id).orElse(null);
        if (product == null) {
            return "redirect:/admin/products";
        }

        // Convert Product to ProductFormDto
        ProductFormDto productFormDto = new ProductFormDto();
        productFormDto.setId(product.getId());
        productFormDto.setName(product.getName());
        productFormDto.setDescription(product.getDescription());
        productFormDto.setPrice(product.getPrice());
        productFormDto.setCategory(product.getCategory());
        productFormDto.setStock(product.getStock());
        productFormDto.setBrand(product.getBrand());
        productFormDto.setModel(product.getModel());
        productFormDto.setSpecifications(product.getSpecifications());
        productFormDto.setImageUrl(product.getImageUrl());
        
        // Get secondary images from JSON
        List<String> secondaryImages = productService.getSecondaryImages(product.getId());
        if (secondaryImages.size() > 0) {
            productFormDto.setCurrentSecondaryImage1(secondaryImages.get(0));
        }
        if (secondaryImages.size() > 1) {
            productFormDto.setCurrentSecondaryImage2(secondaryImages.get(1));
        }
        if (secondaryImages.size() > 2) {
            productFormDto.setCurrentSecondaryImage3(secondaryImages.get(2));
        }

        model.addAttribute("product", productFormDto);
        return "admin/product-form";
    }

    @PostMapping("/products/save")
    public String saveProduct(@Valid @ModelAttribute("product") ProductFormDto productFormDto, 
                            BindingResult bindingResult,
                            Model model,
                            HttpServletRequest request) {
        try {
            // Validation
            if (bindingResult.hasErrors()) {
                model.addAttribute("errors", bindingResult.getFieldErrors());
                return "admin/product-form";
            }

            // Validate required fields manually
            if (productFormDto.getName() == null || productFormDto.getName().trim().isEmpty()) {
                bindingResult.addError(new FieldError("product", "name", "Product name is required"));
                model.addAttribute("errors", bindingResult.getFieldErrors());
                return "admin/product-form";
            }

            if (productFormDto.getPrice() == null || productFormDto.getPrice() <= 0) {
                bindingResult.addError(new FieldError("product", "price", "Price must be greater than 0"));
                model.addAttribute("errors", bindingResult.getFieldErrors());
                return "admin/product-form";
            }

            if (productFormDto.getStock() == null || productFormDto.getStock() < 0) {
                bindingResult.addError(new FieldError("product", "stock", "Stock cannot be negative"));
                model.addAttribute("errors", bindingResult.getFieldErrors());
                return "admin/product-form";
            }

            // Convert DTO to Entity
            Product product = new Product();
            product.setId(productFormDto.getId());
            product.setName(productFormDto.getName());
            product.setDescription(productFormDto.getDescription());
            product.setPrice(productFormDto.getPrice());
            product.setCategory(productFormDto.getCategory());
            product.setStock(productFormDto.getStock());
            // Set default values for optional fields
            product.setBrand(productFormDto.getBrand() != null && !productFormDto.getBrand().trim().isEmpty()
                    ? productFormDto.getBrand().trim() : "Unknown");
            product.setModel(productFormDto.getModel() != null && !productFormDto.getModel().trim().isEmpty()
                    ? productFormDto.getModel().trim() : "Unknown");
            product.setSpecifications(productFormDto.getSpecifications());

            // Preserve existing images if not uploading new ones
            if (productFormDto.getId() != null) {
                Product existingProduct = productService.getProductById(productFormDto.getId()).orElse(null);
                if (existingProduct != null) {
                    product.setImageUrl(existingProduct.getImageUrl());
                    product.setSecondaryImages(existingProduct.getSecondaryImages());
                }
            }

            Product savedProduct;
            if (product.getId() == null) {
                // Create new product
                savedProduct = productService.createProduct(product);
            } else {
                // Update existing product
                savedProduct = productService.updateProduct(product.getId(), product);
            }

            // Handle main image upload
            if (productFormDto.getImageFile() != null && !productFormDto.getImageFile().isEmpty()) {
                String contentType = productFormDto.getImageFile().getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    bindingResult.addError(new FieldError("product", "imageFile", "File must be an image"));
                    model.addAttribute("errors", bindingResult.getFieldErrors());
                    return "admin/product-form";
                }

                try {
                    // Validate file using LocalImageService
                    if (!localImageService.isValidImageFile(productFormDto.getImageFile())) {
                        bindingResult.addError(new FieldError("product", "imageFile", "Invalid image file type"));
                        model.addAttribute("errors", bindingResult.getFieldErrors());
                        return "admin/product-form";
                    }
                    
                    if (localImageService.getFileSizeInMB(productFormDto.getImageFile()) > 5.0) {
                        bindingResult.addError(new FieldError("product", "imageFile", "File size too large (max 5MB)"));
                        model.addAttribute("errors", bindingResult.getFieldErrors());
                        return "admin/product-form";
                    }
                    
                    String mainImageUrl = productService.uploadProductImage(savedProduct.getId(), productFormDto.getImageFile());
                    savedProduct.setImageUrl(mainImageUrl);
                    productRepository.save(savedProduct);
                } catch (Exception e) {
                    System.err.println("Error uploading main image: " + e.getMessage());
                    e.printStackTrace();
                    bindingResult.addError(new FieldError("product", "imageFile", "Error uploading main image: " + e.getMessage()));
                    model.addAttribute("errors", bindingResult.getFieldErrors());
                    return "admin/product-form";
                }
            } else if (productFormDto.getId() == null) {
                // Require main image for new products
                bindingResult.addError(new FieldError("product", "imageFile", "Main image is required for new products"));
                model.addAttribute("errors", bindingResult.getFieldErrors());
                return "admin/product-form";
            }

            // Handle secondary images upload
            try {
                List<MultipartFile> secondaryImages = new ArrayList<>();
                
                // Check for multiple files from new input
                if (request instanceof MultipartHttpServletRequest) {
                    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                    List<MultipartFile> secondaryImageFiles = multipartRequest.getFiles("secondaryImages");
                    if (secondaryImageFiles != null && !secondaryImageFiles.isEmpty()) {
                        for (MultipartFile file : secondaryImageFiles) {
                            if (file != null && !file.isEmpty()) {
                                secondaryImages.add(file);
                            }
                        }
                    }
                }
                
                // Fallback to individual files for backward compatibility
                if (secondaryImages.isEmpty()) {
                    if (productFormDto.getSecondaryImage1() != null && !productFormDto.getSecondaryImage1().isEmpty()) {
                        secondaryImages.add(productFormDto.getSecondaryImage1());
                    }
                    if (productFormDto.getSecondaryImage2() != null && !productFormDto.getSecondaryImage2().isEmpty()) {
                        secondaryImages.add(productFormDto.getSecondaryImage2());
                    }
                    if (productFormDto.getSecondaryImage3() != null && !productFormDto.getSecondaryImage3().isEmpty()) {
                        secondaryImages.add(productFormDto.getSecondaryImage3());
                    }
                }
                
                if (!secondaryImages.isEmpty()) {
                    productService.uploadSecondaryImages(savedProduct.getId(), secondaryImages);
                }
                
            } catch (Exception e) {
                bindingResult.addError(new FieldError("product", "imageFile", "Error uploading secondary images: " + e.getMessage()));
                model.addAttribute("errors", bindingResult.getFieldErrors());
                return "admin/product-form";
            }

            return "redirect:/admin/products?success=Product saved successfully";
        } catch (Exception e) {
            System.err.println("Error in saveProduct: " + e.getMessage());
            e.printStackTrace();
            // Use a simple error message to avoid URL encoding issues
            return "redirect:/admin/products?error=Error saving product";
        }
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return "redirect:/admin/products?success=Sản phẩm đã được xóa thành công";
        } catch (Exception e) {
            return "redirect:/admin/products?error=Lỗi xóa sản phẩm: " + e.getMessage();
        }
    }

    @PostMapping("/products/bulk-delete")
    public String bulkDeleteProducts(@RequestParam("productIds") List<Long> productIds) {
        try {
            for (Long id : productIds) {
                productService.deleteProduct(id);
            }
            return "redirect:/admin/products?success=" + productIds.size() + " sản phẩm đã được xóa thành công";
        } catch (Exception e) {
            return "redirect:/admin/products?error=Lỗi xóa hàng loạt: " + e.getMessage();
        }
    }

    @GetMapping("/products/export")
    public String exportProducts() {
        // TODO: Implement CSV/Excel export
        return "redirect:/admin/products?success=Chức năng export đang được phát triển";
    }
}
