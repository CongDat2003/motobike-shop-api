# Hướng dẫn chạy trang Admin Web - MotoBike Shop

## 🚀 Cách chạy trang Admin Web

### Bước 1: Chuẩn bị môi trường

1. **Đảm bảo đã cài đặt:**
   - Java 17 hoặc cao hơn
   - Maven 3.6+
   - MySQL 8.0+
   - IDE (IntelliJ IDEA, Eclipse, hoặc VS Code)

2. **Cấu hình database:**
   - Tạo database MySQL: `motobike_shop`
   - Cập nhật thông tin database trong `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/motobike_shop?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=root
   ```

### Bước 2: Chạy ứng dụng

#### Cách 1: Sử dụng Maven (Khuyến nghị)
```bash
cd C:\Users\Admin\Downloads\motobike-shop-api\motobike-shop-api
mvn clean install
mvn spring-boot:run
```

#### Cách 2: Sử dụng IDE
1. Mở project trong IntelliJ IDEA hoặc Eclipse
2. Tìm file `MotobikeShopApiApplication.java`
3. Click chuột phải → Run 'MotobikeShopApiApplication'

#### Cách 3: Chạy JAR file
```bash
mvn clean package
java -jar target/motobike-shop-api-0.0.1-SNAPSHOT.jar
```

### Bước 3: Truy cập trang Admin

Sau khi ứng dụng chạy thành công, mở trình duyệt và truy cập:

🌐 **URL chính:** `http://localhost:8080/admin`

📋 **Các trang admin có sẵn:**
- **Dashboard:** `http://localhost:8080/admin`
- **Quản lý sản phẩm:** `http://localhost:8080/admin/products`
- **Thêm sản phẩm:** `http://localhost:8080/admin/products/new`
- **Chỉnh sửa sản phẩm:** `http://localhost:8080/admin/products/edit/{id}`

### Bước 4: Sử dụng trang Admin

#### 🏠 Dashboard
- Xem tổng quan số liệu
- Thống kê sản phẩm
- Danh sách sản phẩm gần đây

#### 📦 Quản lý sản phẩm
- **Xem danh sách:** Hiển thị tất cả sản phẩm với bảng đẹp
- **Thêm mới:** Click "Thêm sản phẩm" → Điền form → Lưu
- **Chỉnh sửa:** Click icon ✏️ → Sửa thông tin → Lưu
- **Xóa:** Click icon 🗑️ → Xác nhận xóa

#### 📝 Form thêm/sửa sản phẩm
**Thông tin bắt buộc:**
- ✅ Tên sản phẩm
- ✅ Giá bán
- ✅ Số lượng tồn kho
- ✅ Danh mục
- ✅ Thương hiệu

**Thông tin tùy chọn:**
- 📄 Mô tả sản phẩm
- 🏷️ Model/Dòng sản phẩm
- ⚙️ Thông số kỹ thuật
- 🖼️ Hình ảnh sản phẩm

### Bước 5: Tính năng nâng cao

#### 📤 Upload hình ảnh
1. Chọn file ảnh trong form
2. Hệ thống tự động upload lên Cloudinary
3. URL ảnh được lưu vào database

#### 🔍 Tìm kiếm sản phẩm
- Sử dụng ô tìm kiếm trong trang quản lý sản phẩm
- Tìm theo tên, thương hiệu, danh mục

#### 📊 Thống kê
- Tổng số sản phẩm
- Tổng tồn kho
- Sản phẩm hết hàng

## 🛠️ Troubleshooting

### Lỗi thường gặp:

#### 1. **Lỗi kết nối database:**
```
Error: Could not create connection to database server
```
**Giải pháp:**
- Kiểm tra MySQL đã chạy chưa
- Kiểm tra username/password trong `application.properties`
- Đảm bảo database `motobike_shop` đã được tạo

#### 2. **Lỗi port đã được sử dụng:**
```
Port 8080 was already in use
```
**Giải pháp:**
- Tìm và kill process đang sử dụng port 8080
- Hoặc đổi port trong `application.properties`: `server.port=8081`

#### 3. **Lỗi Cloudinary:**
```
Cloudinary configuration error
```
**Giải pháp:**
- Kiểm tra thông tin Cloudinary trong `application.properties`
- Đảm bảo có kết nối internet

#### 4. **Lỗi template không tìm thấy:**
```
Template not found: admin/dashboard
```
**Giải pháp:**
- Kiểm tra file template có trong `src/main/resources/templates/admin/`
- Đảm bảo đã thêm dependency Thymeleaf

## 📱 Kết nối với App Mobile

Sau khi admin web hoạt động, app mobile sẽ tự động:
- ✅ Lấy danh sách sản phẩm từ API
- ✅ Hiển thị sản phẩm mới được thêm từ admin
- ✅ Cập nhật thông tin sản phẩm real-time

## 🔧 Cấu hình nâng cao

### Thay đổi port:
```properties
# Trong application.properties
server.port=8081
```

### Thay đổi database:
```properties
# Trong application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Thêm authentication cho admin:
```java
// Trong SecurityConfig.java
.requestMatchers("/admin/**").hasRole("ADMIN")
```

## 📞 Hỗ trợ

Nếu gặp vấn đề, hãy kiểm tra:
1. ✅ Java version (cần Java 17+)
2. ✅ Maven version (cần Maven 3.6+)
3. ✅ MySQL đang chạy
4. ✅ Port 8080 không bị chiếm
5. ✅ Kết nối internet (cho Cloudinary)

---

🎉 **Chúc bạn sử dụng trang admin thành công!**


