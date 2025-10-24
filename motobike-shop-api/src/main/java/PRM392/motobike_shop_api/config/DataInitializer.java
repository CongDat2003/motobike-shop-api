package PRM392.motobike_shop_api.config;

import PRM392.motobike_shop_api.entity.Product;
import PRM392.motobike_shop_api.entity.User;
import PRM392.motobike_shop_api.repository.ProductRepository;
import PRM392.motobike_shop_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Create sample users
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@motobike.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFullName("Administrator");
            admin.setPhoneNumber("0123456789");
            admin.setAddress("Hà Nội");
            admin.setRole(User.Role.ADMIN);
            userRepository.save(admin);
            
            User user = new User();
            user.setUsername("user");
            user.setEmail("user@motobike.com");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setFullName("Nguyễn Văn A");
            user.setPhoneNumber("0987654321");
            user.setAddress("TP.HCM");
            userRepository.save(user);
        }
        
        // Create sample products
        if (productRepository.count() == 0) {
            Product product1 = new Product();
            product1.setName("Phanh đĩa Honda Wave");
            product1.setDescription("Phanh đĩa chính hãng Honda Wave, chất lượng cao, bền bỉ");
            product1.setPrice(250000.0);
            product1.setImageUrl("");
            product1.setCategory("Phanh");
            product1.setStock(10);
            product1.setBrand("Honda");
            product1.setModel("Wave");
            product1.setSpecifications("• Đường kính: 240mm\n• Chất liệu: Thép không gỉ\n• Bảo hành: 12 tháng");
            productRepository.save(product1);
            
            Product product2 = new Product();
            product2.setName("Lốp xe Yamaha Exciter");
            product2.setDescription("Lốp xe cao cấp Yamaha Exciter, độ bám đường tốt");
            product2.setPrice(450000.0);
            product2.setImageUrl("");
            product2.setCategory("Lốp xe");
            product2.setStock(5);
            product2.setBrand("Yamaha");
            product2.setModel("Exciter");
            product2.setSpecifications("• Kích thước: 100/80-17\n• Chất liệu: Cao su tổng hợp\n• Bảo hành: 6 tháng");
            productRepository.save(product2);
            
            // Dầu nhớt cho xe máy thông thường
            Product product3 = new Product();
            product3.setName("Dầu nhớt Motul 5100 4T 10W-40");
            product3.setDescription("Dầu nhớt động cơ Motul 5100 4T, hiệu suất cao cho xe máy");
            product3.setPrice(180000.0);
            product3.setImageUrl("");
            product3.setCategory("Dầu nhớt");
            product3.setStock(20);
            product3.setBrand("Motul");
            product3.setModel("5100 4T");
            product3.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 1L\n• Loại: 4-stroke\n• Công nghệ: Ester\n• Bảo hành: 3 tháng");
            productRepository.save(product3);
            
            Product product11 = new Product();
            product11.setName("Dầu nhớt Motul 7000 4T 10W-40");
            product11.setDescription("Dầu nhớt động cơ Motul 7000 4T, thể thao và đi lại");
            product11.setPrice(220000.0);
            product11.setImageUrl("");
            product11.setCategory("Dầu nhớt");
            product11.setStock(15);
            product11.setBrand("Motul");
            product11.setModel("7000 4T");
            product11.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 1L\n• Loại: 4-stroke\n• Công nghệ: 100% Tổng hợp\n• Bảo hành: 6 tháng");
            productRepository.save(product11);
            
            Product product12 = new Product();
            product12.setName("Dầu nhớt Motul 7100 4T 10W-40");
            product12.setDescription("Dầu nhớt động cơ Motul 7100 4T, thể thao và phiêu lưu");
            product12.setPrice(280000.0);
            product12.setImageUrl("");
            product12.setCategory("Dầu nhớt");
            product12.setStock(12);
            product12.setBrand("Motul");
            product12.setModel("7100 4T");
            product12.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 1L\n• Loại: 4-stroke\n• Công nghệ: Ester 100% Tổng hợp\n• Bảo hành: 6 tháng");
            productRepository.save(product12);
            
            Product product13 = new Product();
            product13.setName("Dầu nhớt Motul 300V 4T 10W-40");
            product13.setDescription("Dầu nhớt động cơ Motul 300V 4T, đua xe và đường phố");
            product13.setPrice(350000.0);
            product13.setImageUrl("");
            product13.setCategory("Dầu nhớt");
            product13.setStock(8);
            product13.setBrand("Motul");
            product13.setModel("300V 4T");
            product13.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 1L\n• Loại: 4-stroke\n• Công nghệ: Ester Core 100% Tổng hợp\n• Bảo hành: 12 tháng");
            productRepository.save(product13);
            
            // Dầu nhớt cho xe tay ga
            Product product14 = new Product();
            product14.setName("Dầu nhớt Liqui Moly Molygen 4T 10W-40 Scooter");
            product14.setDescription("Dầu nhớt động cơ Liqui Moly Molygen 4T, dành cho xe tay ga");
            product14.setPrice(200000.0);
            product14.setImageUrl("");
            product14.setCategory("Dầu nhớt");
            product14.setStock(18);
            product14.setBrand("Liqui Moly");
            product14.setModel("Molygen 4T");
            product14.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 1L\n• Loại: 4-stroke Scooter\n• Công nghệ: Advanced Full Synthetic\n• Bảo hành: 6 tháng");
            productRepository.save(product14);
            
            Product product15 = new Product();
            product15.setName("Dầu nhớt Shell Advance City Scooter 5W-40");
            product15.setDescription("Dầu nhớt động cơ Shell Advance City, dành cho xe tay ga");
            product15.setPrice(160000.0);
            product15.setImageUrl("");
            product15.setCategory("Dầu nhớt");
            product15.setStock(25);
            product15.setBrand("Shell");
            product15.setModel("Advance City");
            product15.setSpecifications("• Độ nhớt: 5W-40\n• Dung tích: 800ml\n• Loại: 4-stroke Scooter\n• Công nghệ: 100% Tổng hợp\n• Bảo hành: 6 tháng");
            productRepository.save(product15);
            
            Product product16 = new Product();
            product16.setName("Dầu nhớt Mobil Super Moto Scooter 10W-40");
            product16.setDescription("Dầu nhớt động cơ Mobil Super Moto, dành cho xe tay ga");
            product16.setPrice(170000.0);
            product16.setImageUrl("");
            product16.setCategory("Dầu nhớt");
            product16.setStock(20);
            product16.setBrand("Mobil");
            product16.setModel("Super Moto");
            product16.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 800ml\n• Loại: 4-AT Scooter\n• Công nghệ: Synthetic Technology\n• Bảo hành: 6 tháng");
            productRepository.save(product16);
            
            // Dầu nhớt cao cấp
            Product product17 = new Product();
            product17.setName("Dầu nhớt Repsol Racing 4T 10W-40");
            product17.setDescription("Dầu nhớt động cơ Repsol Racing 4T, cao cấp cho đua xe");
            product17.setPrice(320000.0);
            product17.setImageUrl("");
            product17.setCategory("Dầu nhớt");
            product17.setStock(10);
            product17.setBrand("Repsol");
            product17.setModel("Racing 4T");
            product17.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 1L\n• Loại: 4-stroke\n• Công nghệ: Fully Synthetic\n• Bảo hành: 12 tháng");
            productRepository.save(product17);
            
            Product product18 = new Product();
            product18.setName("Dầu nhớt Fuchs Silkolene Pro 4 XP 10W-40");
            product18.setDescription("Dầu nhớt động cơ Fuchs Silkolene Pro 4 XP, công nghệ Đức");
            product18.setPrice(250000.0);
            product18.setImageUrl("");
            product18.setCategory("Dầu nhớt");
            product18.setStock(14);
            product18.setBrand("Fuchs");
            product18.setModel("Silkolene Pro 4 XP");
            product18.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 1L\n• Loại: 4-stroke\n• Công nghệ: Fully Synthetic\n• Bảo hành: 6 tháng");
            productRepository.save(product18);

            // Dầu nhớt Mobil
            Product product19 = new Product();
            product19.setName("Dầu nhớt Mobil Super Moto 10W-40 4T");
            product19.setDescription("Dầu nhớt động cơ Mobil Super Moto 10W-40 4T, công nghệ tổng hợp cao cấp");
            product19.setPrice(180000.0);
            product19.setImageUrl("/images/mobil-super-moto-10w40.jpg");
            product19.setCategory("Dầu nhớt");
            product19.setStock(22);
            product19.setBrand("Mobil");
            product19.setModel("Super Moto 4T");
            product19.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 800ml\n• Loại: 4-stroke\n• Công nghệ: Synthetic Technology\n• Tiêu chuẩn: JASO MA2, API SL\n• Bảo hành: 6 tháng");
            productRepository.save(product19);

            Product product20 = new Product();
            product20.setName("Dầu nhớt Mobil Super Moto 10W-30 4T");
            product20.setDescription("Dầu nhớt động cơ Mobil Super Moto 10W-30 4T, hiệu suất tối ưu");
            product20.setPrice(175000.0);
            product20.setImageUrl("/images/mobil-super-moto-10w30.jpg");
            product20.setCategory("Dầu nhớt");
            product20.setStock(20);
            product20.setBrand("Mobil");
            product20.setModel("Super Moto 4T");
            product20.setSpecifications("• Độ nhớt: 10W-30\n• Dung tích: 800ml\n• Loại: 4-stroke\n• Công nghệ: Synthetic Technology\n• Tiêu chuẩn: JASO MA2, API SL\n• Bảo hành: 6 tháng");
            productRepository.save(product20);

            Product product21 = new Product();
            product21.setName("Dầu nhớt Mobil 1 Racing 4T 10W-40");
            product21.setDescription("Dầu nhớt động cơ Mobil 1 Racing 4T 10W-40, cao cấp cho đua xe");
            product21.setPrice(350000.0);
            product21.setImageUrl("/images/mobil-1-racing-10w40.jpg");
            product21.setCategory("Dầu nhớt");
            product21.setStock(8);
            product21.setBrand("Mobil");
            product21.setModel("1 Racing 4T");
            product21.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 1L\n• Loại: 4-stroke Racing\n• Công nghệ: Advanced Full Synthetic\n• Tiêu chuẩn: JASO MA2, API SL\n• Bảo hành: 12 tháng");
            productRepository.save(product21);

            // Dầu nhớt AMSOIL
            Product product22 = new Product();
            product22.setName("Dầu nhớt AMSOIL 100% Synthetic 10W-40 4T");
            product22.setDescription("Dầu nhớt động cơ AMSOIL 100% Synthetic 10W-40 4T, công nghệ Mỹ");
            product22.setPrice(320000.0);
            product22.setImageUrl("https://via.placeholder.com/300x300/4CAF50/FFFFFF?text=AMSOIL+100%25");
            product22.setCategory("Dầu nhớt");
            product22.setStock(12);
            product22.setBrand("AMSOIL");
            product22.setModel("100% Synthetic 4T");
            product22.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 946ml\n• Loại: 4-stroke\n• Công nghệ: 100% Synthetic\n• Tiêu chuẩn: JASO MA/MA2, API SG/SL/CF\n• Xuất xứ: Made in USA\n• Bảo hành: 12 tháng");
            productRepository.save(product22);

            Product product23 = new Product();
            product23.setName("Dầu nhớt AMSOIL 4-Stroke Scooter Oil 10W-40");
            product23.setDescription("Dầu nhớt động cơ AMSOIL 4-Stroke Scooter Oil 10W-40, chuyên cho xe tay ga");
            product23.setPrice(280000.0);
            product23.setImageUrl("");
            product23.setCategory("Dầu nhớt");
            product23.setStock(15);
            product23.setBrand("AMSOIL");
            product23.setModel("Formula 4-Stroke");
            product23.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 946ml\n• Loại: 4-stroke Scooter\n• Công nghệ: 100% Synthetic\n• Tiêu chuẩn: JASO MA/MA2, API SG/SL/CF\n• Xuất xứ: Made in USA\n• Bảo hành: 12 tháng");
            productRepository.save(product23);

            // Dầu nhớt IPONE
            Product product24 = new Product();
            product24.setName("Dầu nhớt IPONE R4000 RS 10W-40 4T");
            product24.setDescription("Dầu nhớt động cơ IPONE R4000 RS 10W-40 4T, công nghệ Ester cao cấp");
            product24.setPrice(250000.0);
            product24.setImageUrl("");
            product24.setCategory("Dầu nhớt");
            product24.setStock(18);
            product24.setBrand("IPONE");
            product24.setModel("R4000 RS 4T");
            product24.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 1L\n• Loại: 4-stroke\n• Công nghệ: Ester + Gear Protection\n• Tiêu chuẩn: JASO MA2, API SM\n• Bảo vệ hộp số: Có\n• Bảo hành: 6 tháng");
            productRepository.save(product24);

            // Dầu nhớt Repsol
            Product product25 = new Product();
            product25.setName("Dầu nhớt Repsol Smarter 4T 10W-40");
            product25.setDescription("Dầu nhớt động cơ Repsol Smarter 4T 10W-40, thông minh và hiệu quả");
            product25.setPrice(220000.0);
            product25.setImageUrl("");
            product25.setCategory("Dầu nhớt");
            product25.setStock(25);
            product25.setBrand("Repsol");
            product25.setModel("Smarter 4T");
            product25.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 1L\n• Loại: 4-stroke\n• Công nghệ: Fully Synthetic\n• Tiêu chuẩn: API SN, JASO MA2\n• Bảo hành: 6 tháng");
            productRepository.save(product25);

            Product product26 = new Product();
            product26.setName("Dầu nhớt Repsol MXR Platinum 4T 10W-40");
            product26.setDescription("Dầu nhớt động cơ Repsol MXR Platinum 4T 10W-40, bạch kim cao cấp");
            product26.setPrice(300000.0);
            product26.setImageUrl("");
            product26.setCategory("Dầu nhớt");
            product26.setStock(10);
            product26.setBrand("Repsol");
            product26.setModel("MXR Platinum 4T");
            product26.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 800ml\n• Loại: 4-stroke\n• Công nghệ: Fully Synthetic\n• Tiêu chuẩn: API SN, JASO MA2\n• Bảo hành: 12 tháng");
            productRepository.save(product26);

            // Dầu nhớt Shell
            Product product27 = new Product();
            product27.setName("Dầu nhớt Shell Advance Ultra 10W-40 4T");
            product27.setDescription("Dầu nhớt động cơ Shell Advance Ultra 10W-40 4T, công nghệ PurePlus");
            product27.setPrice(200000.0);
            product27.setImageUrl("/images/shell-advance-ultra-10w40.jpg");
            product27.setCategory("Dầu nhớt");
            product27.setStock(30);
            product27.setBrand("Shell");
            product27.setModel("Advance Ultra 4T");
            product27.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 1L\n• Loại: 4-stroke\n• Công nghệ: 100% Synthetic\n• Tiêu chuẩn: API SN, JASO MA2\n• Bảo hành: 6 tháng");
            productRepository.save(product27);

            Product product28 = new Product();
            product28.setName("Dầu nhớt Shell Advance AX7 10W-40 4T");
            product28.setDescription("Dầu nhớt động cơ Shell Advance AX7 10W-40 4T, công nghệ Active Cleansing");
            product28.setPrice(180000.0);
            product28.setImageUrl("");
            product28.setCategory("Dầu nhớt");
            product28.setStock(28);
            product28.setBrand("Shell");
            product28.setModel("Advance AX7 4T");
            product28.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 800ml\n• Loại: 4-stroke\n• Công nghệ: Active Cleansing Technology\n• Tiêu chuẩn: API SN, JASO MA2\n• Bảo hành: 6 tháng");
            productRepository.save(product28);

            Product product29 = new Product();
            product29.setName("Dầu nhớt Shell Advance City 10W-40 4T");
            product29.setDescription("Dầu nhớt động cơ Shell Advance City 10W-40 4T, dành cho thành phố");
            product29.setPrice(160000.0);
            product29.setImageUrl("");
            product29.setCategory("Dầu nhớt");
            product29.setStock(35);
            product29.setBrand("Shell");
            product29.setModel("Advance City 4T");
            product29.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 800ml\n• Loại: 4-stroke\n• Công nghệ: 100% Synthetic\n• Tiêu chuẩn: API SN, JASO MA2\n• Bảo hành: 6 tháng");
            productRepository.save(product29);

            // Dầu nhớt Liqui Moly
            Product product30 = new Product();
            product30.setName("Dầu nhớt Liqui Moly Street 4T 10W-40");
            product30.setDescription("Dầu nhớt động cơ Liqui Moly Street 4T 10W-40, công nghệ Đức");
            product30.setPrice(240000.0);
            product30.setImageUrl("");
            product30.setCategory("Dầu nhớt");
            product30.setStock(20);
            product30.setBrand("Liqui Moly");
            product30.setModel("Street 4T");
            product30.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 1L\n• Loại: 4-stroke\n• Công nghệ: Synthese Technologie\n• Tiêu chuẩn: API SP Plus, JASO MA2\n• Xuất xứ: Made in Germany\n• Bảo hành: 6 tháng");
            productRepository.save(product30);

            Product product31 = new Product();
            product31.setName("Dầu nhớt Liqui Moly Scooter Race 4T 10W-40");
            product31.setDescription("Dầu nhớt động cơ Liqui Moly Scooter Race 4T 10W-40, đua xe tay ga");
            product31.setPrice(280000.0);
            product31.setImageUrl("");
            product31.setCategory("Dầu nhớt");
            product31.setStock(12);
            product31.setBrand("Liqui Moly");
            product31.setModel("Scooter Race 4T");
            product31.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 1L\n• Loại: 4-stroke Scooter Race\n• Công nghệ: Vollsynthetisches Motorenöl\n• Tiêu chuẩn: API SN Plus, JASO MA2\n• Xuất xứ: Made in Germany\n• Bảo hành: 12 tháng");
            productRepository.save(product31);

            Product product32 = new Product();
            product32.setName("Dầu nhớt Liqui Moly 4T 10W-40 Scooter MB");
            product32.setDescription("Dầu nhớt động cơ Liqui Moly 4T 10W-40 Scooter MB, chuyên cho xe tay ga");
            product32.setPrice(200000.0);
            product32.setImageUrl("");
            product32.setCategory("Dầu nhớt");
            product32.setStock(25);
            product32.setBrand("Liqui Moly");
            product32.setModel("4T Scooter MB");
            product32.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 800ml\n• Loại: 4-stroke Scooter\n• Công nghệ: Mineralisches Motoröl\n• Tiêu chuẩn: API SN Plus, JASO MB\n• Xuất xứ: Made in Germany\n• Bảo hành: 6 tháng");
            productRepository.save(product32);

            // Dầu nhớt Motul cho xe tay ga
            Product product33 = new Product();
            product33.setName("Dầu nhớt Motul Scooter Expert LE MB 10W-40");
            product33.setDescription("Dầu nhớt động cơ Motul Scooter Expert LE MB 10W-40, chuyên gia xe tay ga");
            product33.setPrice(190000.0);
            product33.setImageUrl("/images/motul-scooter-expert-10w40.jpg");
            product33.setCategory("Dầu nhớt");
            product33.setStock(28);
            product33.setBrand("Motul");
            product33.setModel("Scooter Expert LE MB");
            product33.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 800ml\n• Loại: 4-stroke Scooter\n• Công nghệ: Technosynthese\n• Tiêu chuẩn: JASO MB\n• Bảo hành: 6 tháng");
            productRepository.save(product33);

            Product product34 = new Product();
            product34.setName("Dầu nhớt Motul Scooter Power LE MB 5W-40");
            product34.setDescription("Dầu nhớt động cơ Motul Scooter Power LE MB 5W-40, sức mạnh xe tay ga");
            product34.setPrice(200000.0);
            product34.setImageUrl("");
            product34.setCategory("Dầu nhớt");
            product34.setStock(24);
            product34.setBrand("Motul");
            product34.setModel("Scooter Power LE MB");
            product34.setSpecifications("• Độ nhớt: 5W-40\n• Dung tích: 1L\n• Loại: 4-stroke Scooter\n• Công nghệ: 100% Tổng hợp\n• Tiêu chuẩn: JASO MB\n• Bảo hành: 6 tháng");
            productRepository.save(product34);

            // Dầu nhớt Shell cho xe tay ga
            Product product35 = new Product();
            product35.setName("Dầu nhớt Shell Advance AX7 Scooter 10W-40");
            product35.setDescription("Dầu nhớt động cơ Shell Advance AX7 Scooter 10W-40, chuyên cho xe tay ga");
            product35.setPrice(150000.0);
            product35.setImageUrl("");
            product35.setCategory("Dầu nhớt");
            product35.setStock(32);
            product35.setBrand("Shell");
            product35.setModel("Advance AX7 Scooter");
            product35.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 800ml\n• Loại: 4-stroke Scooter\n• Công nghệ: Flexi Molecule Technology\n• Tiêu chuẩn: API SN, JASO MB\n• Bảo hành: 6 tháng");
            productRepository.save(product35);

            // Dầu nhớt Fuchs cho xe tay ga
            Product product36 = new Product();
            product36.setName("Dầu nhớt Fuchs Silkolene Scoot Sport 4 5W-40");
            product36.setDescription("Dầu nhớt động cơ Fuchs Silkolene Scoot Sport 4 5W-40, thể thao xe tay ga");
            product36.setPrice(180000.0);
            product36.setImageUrl("");
            product36.setCategory("Dầu nhớt");
            product36.setStock(20);
            product36.setBrand("Fuchs");
            product36.setModel("Silkolene Scoot Sport 4");
            product36.setSpecifications("• Độ nhớt: 5W-40\n• Dung tích: 1L\n• Loại: 4-stroke Scooter\n• Công nghệ: Fully Synthetic\n• Tiêu chuẩn: API SN, JASO MB\n• Xuất xứ: German Technology\n• Bảo hành: 6 tháng");
            productRepository.save(product36);

            // Dầu nhớt cao cấp
            Product product37 = new Product();
            product37.setName("Dầu nhớt Wolver Racing 4T 10W-40");
            product37.setDescription("Dầu nhớt động cơ Wolver Racing 4T 10W-40, đua xe cao cấp");
            product37.setPrice(280000.0);
            product37.setImageUrl("");
            product37.setCategory("Dầu nhớt");
            product37.setStock(15);
            product37.setBrand("Wolver");
            product37.setModel("Racing 4T");
            product37.setSpecifications("• Độ nhớt: 10W-40\n• Dung tích: 1L\n• Loại: 4-stroke Racing\n• Công nghệ: Synthetic\n• Tiêu chuẩn: API SL, JASO MA2\n• Bảo hành: 12 tháng");
            productRepository.save(product37);

            // Dầu hộp số/Truyền động
            Product product38 = new Product();
            product38.setName("Dầu hộp số Mobil Super Moto Scooter Gear Oil 80W-90");
            product38.setDescription("Dầu hộp số Mobil Super Moto Scooter Gear Oil 80W-90, chuyên cho xe tay ga");
            product38.setPrice(120000.0);
            product38.setImageUrl("");
            product38.setCategory("Phụ tùng");
            product38.setStock(25);
            product38.setBrand("Mobil");
            product38.setModel("Super Moto Gear Oil");
            product38.setSpecifications("• Độ nhớt: 80W-90\n• Dung tích: 120ml\n• Loại: Gear Oil\n• Ứng dụng: Scooter\n• Bảo hành: 6 tháng");
            productRepository.save(product38);

            Product product39 = new Product();
            product39.setName("Dầu hộp số Liqui Moly Gear Oil GL4 80W-90 Scooter");
            product39.setDescription("Dầu hộp số Liqui Moly Gear Oil GL4 80W-90 Scooter, công nghệ Đức");
            product39.setPrice(150000.0);
            product39.setImageUrl("");
            product39.setCategory("Phụ tùng");
            product39.setStock(20);
            product39.setBrand("Liqui Moly");
            product39.setModel("Gear Oil GL4");
            product39.setSpecifications("• Độ nhớt: 80W-90\n• Loại: Gear Oil GL4\n• Ứng dụng: Scooter\n• Tiêu chuẩn: API GL4, MIL-L 2105\n• Xuất xứ: Made in Germany\n• Bảo hành: 6 tháng");
            productRepository.save(product39);

            Product product40 = new Product();
            product40.setName("Dầu hộp số Motul Scooter Gear Plus 80W-90");
            product40.setDescription("Dầu hộp số Motul Scooter Gear Plus 80W-90, chuyên cho xe tay ga tự động");
            product40.setPrice(140000.0);
            product40.setImageUrl("");
            product40.setCategory("Phụ tùng");
            product40.setStock(22);
            product40.setBrand("Motul");
            product40.setModel("Scooter Gear Plus");
            product40.setSpecifications("• Độ nhớt: 80W-90\n• Loại: Gear Oil\n• Ứng dụng: Automatic Scooter\n• Công nghệ: Technosynthese\n• Bảo hành: 6 tháng");
            productRepository.save(product40);

            Product product41 = new Product();
            product41.setName("Dầu hộp số Wolver Multigrade Gear Oil 80W-90");
            product41.setDescription("Dầu hộp số Wolver Multigrade Gear Oil 80W-90, đa cấp độ");
            product41.setPrice(100000.0);
            product41.setImageUrl("");
            product41.setCategory("Phụ tùng");
            product41.setStock(30);
            product41.setBrand("Wolver");
            product41.setModel("Multigrade Gear Oil");
            product41.setSpecifications("• Độ nhớt: 80W-90\n• Loại: Multigrade Gear Oil\n• Ứng dụng: Đa dạng\n• Bảo hành: 6 tháng");
            productRepository.save(product41);

            // Phụ gia động cơ
            Product product42 = new Product();
            product42.setName("Phụ gia Liqui Moly Engine Flush SHOOTER 80ml");
            product42.setDescription("Phụ gia Liqui Moly Engine Flush SHOOTER 80ml, làm sạch động cơ");
            product42.setPrice(120000.0);
            product42.setImageUrl("");
            product42.setCategory("Phụ tùng");
            product42.setStock(35);
            product42.setBrand("Liqui Moly");
            product42.setModel("Engine Flush SHOOTER");
            product42.setSpecifications("• Dung tích: 80ml\n• Loại: Engine Flush\n• Công dụng: Làm sạch động cơ\n• Xuất xứ: Made in Germany\n• Bảo hành: 6 tháng");
            productRepository.save(product42);

            Product product43 = new Product();
            product43.setName("Phụ gia Liqui Moly 4T Additive SHOOTER 80ml");
            product43.setDescription("Phụ gia Liqui Moly 4T Additive SHOOTER 80ml, làm sạch hệ thống nhiên liệu");
            product43.setPrice(110000.0);
            product43.setImageUrl("");
            product43.setCategory("Phụ tùng");
            product43.setStock(40);
            product43.setBrand("Liqui Moly");
            product43.setModel("4T Additive SHOOTER");
            product43.setSpecifications("• Dung tích: 80ml\n• Loại: Fuel System Cleaner\n• Công dụng: Làm sạch hệ thống nhiên liệu\n• Xuất xứ: Made in Germany\n• Bảo hành: 6 tháng");
            productRepository.save(product43);

            Product product44 = new Product();
            product44.setName("Phụ gia Liqui Moly MoS2 SHOOTER 20ml");
            product44.setDescription("Phụ gia Liqui Moly MoS2 SHOOTER 20ml, giảm ma sát");
            product44.setPrice(100000.0);
            product44.setImageUrl("");
            product44.setCategory("Phụ tùng");
            product44.setStock(50);
            product44.setBrand("Liqui Moly");
            product44.setModel("MoS2 SHOOTER");
            product44.setSpecifications("• Dung tích: 20ml\n• Loại: Oil Additive\n• Công dụng: Giảm ma sát (Antifriction)\n• Xuất xứ: Made in Germany\n• Bảo hành: 6 tháng");
            productRepository.save(product44);

            Product product45 = new Product();
            product45.setName("Phụ gia Motul Professional Engine Clean 200ml");
            product45.setDescription("Phụ gia Motul Professional Engine Clean 200ml, làm sạch động cơ chuyên nghiệp");
            product45.setPrice(180000.0);
            product45.setImageUrl("");
            product45.setCategory("Phụ tùng");
            product45.setStock(25);
            product45.setBrand("Motul");
            product45.setModel("Professional Engine Clean");
            product45.setSpecifications("• Dung tích: 200ml\n• Loại: Engine Oil Additive\n• Công dụng: Làm sạch động cơ\n• Bảo hành: 6 tháng");
            productRepository.save(product45);

            Product product46 = new Product();
            product46.setName("Phụ gia Motul Professional Fuel System Clean 200ml");
            product46.setDescription("Phụ gia Motul Professional Fuel System Clean 200ml, làm sạch hệ thống nhiên liệu");
            product46.setPrice(160000.0);
            product46.setImageUrl("");
            product46.setCategory("Phụ tùng");
            product46.setStock(30);
            product46.setBrand("Motul");
            product46.setModel("Professional Fuel System Clean");
            product46.setSpecifications("• Dung tích: 200ml\n• Loại: Gasoline Additive\n• Công dụng: Làm sạch hệ thống nhiên liệu\n• Bảo hành: 6 tháng");
            productRepository.save(product46);

            Product product47 = new Product();
            product47.setName("Phụ gia Wolver Motor Flush Adapter 80ml");
            product47.setDescription("Phụ gia Wolver Motor Flush Adapter 80ml, làm sạch và điều chỉnh động cơ");
            product47.setPrice(130000.0);
            product47.setImageUrl("");
            product47.setCategory("Phụ tùng");
            product47.setStock(28);
            product47.setBrand("Wolver");
            product47.setModel("Motor Flush Adapter");
            product47.setSpecifications("• Dung tích: 80ml\n• Loại: Motor Flush Adapter\n• Công dụng: Làm sạch và điều chỉnh động cơ\n• Xuất xứ: Made in Germany\n• Bảo hành: 6 tháng");
            productRepository.save(product47);

            // Dung dịch làm mát
            Product product48 = new Product();
            product48.setName("Dung dịch làm mát Liqui Moly Coolant Ready Mix RAF 12+ 1L");
            product48.setDescription("Dung dịch làm mát Liqui Moly Coolant Ready Mix RAF 12+ 1L, chống đông và làm mát");
            product48.setPrice(150000.0);
            product48.setImageUrl("");
            product48.setCategory("Phụ tùng");
            product48.setStock(20);
            product48.setBrand("Liqui Moly");
            product48.setModel("Coolant Ready Mix RAF 12+");
            product48.setSpecifications("• Dung tích: 1L\n• Loại: Coolant\n• Công dụng: Chống đông và làm mát\n• Nhiệt độ: -20°C đến -90°C\n• Xuất xứ: Made in Germany\n• Bảo hành: 6 tháng");
            productRepository.save(product48);

            Product product49 = new Product();
            product49.setName("Dung dịch làm mát GORACING 1L");
            product49.setDescription("Dung dịch làm mát GORACING 1L, độ sôi cao và giải nhiệt nhanh");
            product49.setPrice(135000.0);
            product49.setImageUrl("");
            product49.setCategory("Phụ tùng");
            product49.setStock(25);
            product49.setBrand("GORACING");
            product49.setModel("Coolant");
            product49.setSpecifications("• Dung tích: 1L\n• Loại: Coolant\n• Công dụng: Độ sôi cao, giải nhiệt nhanh, chống đóng cặn\n• Bảo hành: 6 tháng");
            productRepository.save(product49);

            // Chăm sóc xe
            Product product50 = new Product();
            product50.setName("SORACING Dưỡng bóng dàn áo 175ml");
            product50.setDescription("SORACING Dưỡng bóng dàn áo 175ml, chăm sóc và bảo vệ sơn xe");
            product50.setPrice(175000.0);
            product50.setImageUrl("");
            product50.setCategory("Phụ kiện");
            product50.setStock(40);
            product50.setBrand("SORACING");
            product50.setModel("Dưỡng bóng dàn áo");
            product50.setSpecifications("• Dung tích: 175ml\n• Loại: Car Care\n• Công dụng: Dưỡng bóng dàn áo\n• Bảo hành: 6 tháng");
            productRepository.save(product50);

            Product product51 = new Product();
            product51.setName("SORACING Chăm sóc nhựa nhám 89ml");
            product51.setDescription("SORACING Chăm sóc nhựa nhám 89ml, bảo vệ và làm sạch nhựa xe");
            product51.setPrice(89000.0);
            product51.setImageUrl("");
            product51.setCategory("Phụ kiện");
            product51.setStock(50);
            product51.setBrand("SORACING");
            product51.setModel("Chăm sóc nhựa nhám");
            product51.setSpecifications("• Dung tích: 89ml\n• Loại: Tire & Plastic Cleaner\n• Công dụng: Chăm sóc nhựa nhám\n• Bảo hành: 6 tháng");
            productRepository.save(product51);

            Product product52 = new Product();
            product52.setName("GORACING Vệ sinh sên 85ml");
            product52.setDescription("GORACING Vệ sinh sên 85ml, làm sạch và bảo vệ sên xe");
            product52.setPrice(85000.0);
            product52.setImageUrl("");
            product52.setCategory("Phụ kiện");
            product52.setStock(45);
            product52.setBrand("GORACING");
            product52.setModel("Vệ sinh sên");
            product52.setSpecifications("• Dung tích: 85ml\n• Loại: Chain Cleaner\n• Công dụng: Vệ sinh sên\n• Bảo hành: 6 tháng");
            productRepository.save(product52);

            Product product53 = new Product();
            product53.setName("GORACING Dưỡng sên 135ml");
            product53.setDescription("GORACING Dưỡng sên 135ml, bôi trơn và bảo vệ sên xe");
            product53.setPrice(135000.0);
            product53.setImageUrl("");
            product53.setCategory("Phụ kiện");
            product53.setStock(35);
            product53.setBrand("GORACING");
            product53.setModel("Dưỡng sên");
            product53.setSpecifications("• Dung tích: 135ml\n• Loại: Chain Lubricant\n• Công dụng: Dưỡng sên\n• Bảo hành: 6 tháng");
            productRepository.save(product53);
            
            Product product4 = new Product();
            product4.setName("Mũ bảo hiểm AGV K3 SV");
            product4.setDescription("Mũ bảo hiểm AGV K3 SV, an toàn cao cấp");
            product4.setPrice(1200000.0);
            product4.setImageUrl("");
            product4.setCategory("Mũ bảo hiểm");
            product4.setStock(3);
            product4.setBrand("AGV");
            product4.setModel("K3 SV");
            product4.setSpecifications("• Chuẩn: ECE 22.05\n• Trọng lượng: 1450g\n• Bảo hành: 24 tháng");
            productRepository.save(product4);
            
            Product product5 = new Product();
            product5.setName("Áo mưa cao cấp");
            product5.setDescription("Áo mưa chống thấm nước, chất lượng cao");
            product5.setPrice(150000.0);
            product5.setImageUrl("");
            product5.setCategory("Áo mưa");
            product5.setStock(15);
            product5.setBrand("Generic");
            product5.setModel("Premium");
            product5.setSpecifications("• Chất liệu: PVC\n• Màu sắc: Đa dạng\n• Bảo hành: 6 tháng");
            productRepository.save(product5);
            
            // Thêm sản phẩm động cơ
            Product product6 = new Product();
            product6.setName("Động cơ Honda Wave 110cc");
            product6.setDescription("Động cơ chính hãng Honda Wave 110cc, hiệu suất cao");
            product6.setPrice(3500000.0);
            product6.setImageUrl("");
            product6.setCategory("Động cơ");
            product6.setStock(2);
            product6.setBrand("Honda");
            product6.setModel("Wave 110");
            product6.setSpecifications("• Dung tích: 110cc\n• Công suất: 8.31 HP\n• Bảo hành: 12 tháng");
            productRepository.save(product6);
            
            // Thêm sản phẩm phụ tùng khác
            Product product7 = new Product();
            product7.setName("Bugi NGK CR7E");
            product7.setDescription("Bugi cao cấp NGK CR7E, tăng hiệu suất động cơ");
            product7.setPrice(120000.0);
            product7.setImageUrl("");
            product7.setCategory("Phụ tùng");
            product7.setStock(50);
            product7.setBrand("NGK");
            product7.setModel("CR7E");
            product7.setSpecifications("• Khe hở: 0.7-0.8mm\n• Chất liệu: Iridium\n• Bảo hành: 6 tháng");
            productRepository.save(product7);
            
            Product product8 = new Product();
            product8.setName("Lọc gió K&N");
            product8.setDescription("Lọc gió hiệu suất cao K&N, tăng công suất động cơ");
            product8.setPrice(800000.0);
            product8.setImageUrl("");
            product8.setCategory("Phụ tùng");
            product8.setStock(8);
            product8.setBrand("K&N");
            product8.setModel("High Flow");
            product8.setSpecifications("• Vật liệu: Cotton gauze\n• Tái sử dụng: Có\n• Bảo hành: 12 tháng");
            productRepository.save(product8);
            
            Product product9 = new Product();
            product9.setName("Ống xả Yoshimura");
            product9.setDescription("Ống xả thể thao Yoshimura, âm thanh mạnh mẽ");
            product9.setPrice(2500000.0);
            product9.setImageUrl("");
            product9.setCategory("Phụ tùng");
            product9.setStock(5);
            product9.setBrand("Yoshimura");
            product9.setModel("R-77");
            product9.setSpecifications("• Vật liệu: Titanium\n• Trọng lượng: 1.2kg\n• Bảo hành: 24 tháng");
            productRepository.save(product9);
            
            Product product10 = new Product();
            product10.setName("Găng tay Alpinestars");
            product10.setDescription("Găng tay bảo hộ Alpinestars, an toàn cao cấp");
            product10.setPrice(600000.0);
            product10.setImageUrl("");
            product10.setCategory("Phụ kiện");
            product10.setStock(12);
            product10.setBrand("Alpinestars");
            product10.setModel("GP Pro");
            product10.setSpecifications("• Vật liệu: Da tổng hợp\n• Kích thước: S/M/L/XL\n• Bảo hành: 6 tháng");
            productRepository.save(product10);
        }
    }
}

