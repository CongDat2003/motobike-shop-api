package PRM392.motobike_shop_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import PRM392.motobike_shop_api.entity.Product;
import PRM392.motobike_shop_api.entity.User;
import PRM392.motobike_shop_api.repository.ProductRepository;
import PRM392.motobike_shop_api.repository.UserRepository;

@Component
public class TestDataInitializer implements CommandLineRunner {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Create test products if none exist
        if (productRepository.count() == 0) {
            createTestProducts();
        }
        
        // Create test users if none exist
        if (userRepository.count() == 0) {
            createTestUsers();
        }
    }
    
    private void createTestProducts() {
        Product product1 = new Product();
        product1.setName("Honda Wave RSX");
        product1.setDescription("Xe máy Honda Wave RSX 110cc tiết kiệm nhiên liệu");
        product1.setPrice(25000000.0);
        product1.setCategory("Xe máy");
        product1.setStock(10);
        product1.setBrand("Honda");
        product1.setModel("Wave RSX");
        product1.setSpecifications("Dung tích xi-lanh: 110cc, Công suất: 8.1 PS, Mức tiêu thụ nhiên liệu: 1.8L/100km");
        productRepository.save(product1);
        
        Product product2 = new Product();
        product2.setName("Yamaha Exciter 150");
        product2.setDescription("Xe máy Yamaha Exciter 150cc thể thao");
        product2.setPrice(45000000.0);
        product2.setCategory("Xe máy");
        product2.setStock(5);
        product2.setBrand("Yamaha");
        product2.setModel("Exciter 150");
        product2.setSpecifications("Dung tích xi-lanh: 150cc, Công suất: 15.2 PS, Mức tiêu thụ nhiên liệu: 2.1L/100km");
        productRepository.save(product2);
        
        Product product3 = new Product();
        product3.setName("Dầu nhớt Mobil Super Moto 10W40");
        product3.setDescription("Dầu nhớt động cơ xe máy 10W40");
        product3.setPrice(150000.0);
        product3.setCategory("Dầu nhớt");
        product3.setStock(50);
        product3.setBrand("Mobil");
        product3.setModel("Super Moto 10W40");
        product3.setSpecifications("Độ nhớt: 10W40, Dung tích: 1L, Loại: Dầu nhớt tổng hợp");
        productRepository.save(product3);
    }
    
    private void createTestUsers() {
        User user1 = new User();
        user1.setUsername("admin");
        user1.setEmail("admin@motobike.com");
        user1.setPassword("$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi"); // password: admin
        user1.setFullName("Administrator");
        user1.setRole(User.Role.ADMIN);
        userRepository.save(user1);
        
        User user2 = new User();
        user2.setUsername("customer1");
        user2.setEmail("customer1@email.com");
        user2.setPassword("$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi"); // password: admin
        user2.setFullName("Nguyễn Văn A");
        user2.setRole(User.Role.USER);
        userRepository.save(user2);
        
        User user3 = new User();
        user3.setUsername("customer2");
        user3.setEmail("customer2@email.com");
        user3.setPassword("$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi"); // password: admin
        user3.setFullName("Trần Thị B");
        user3.setRole(User.Role.USER);
        userRepository.save(user3);
    }
}
