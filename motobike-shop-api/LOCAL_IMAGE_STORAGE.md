# Hệ thống lưu trữ ảnh Local

## Tổng quan
Hệ thống đã được cập nhật để sử dụng lưu trữ ảnh local thay vì Cloudinary, giúp:
- Không phụ thuộc vào dịch vụ bên ngoài
- Tiết kiệm chi phí
- Dễ dàng quản lý và backup
- Tốc độ upload nhanh hơn

## Cấu trúc thư mục
```
src/main/resources/static/images/uploads/
├── [UUID].jpg          # Ảnh chính sản phẩm
├── [UUID].png          # Ảnh phụ sản phẩm
└── [UUID].gif          # Các định dạng khác
```

## Tính năng
- ✅ Upload ảnh chính (bắt buộc cho sản phẩm mới)
- ✅ Upload nhiều ảnh phụ (không giới hạn số lượng)
- ✅ Validation file type (JPG, PNG, GIF, WEBP)
- ✅ Giới hạn kích thước file (5MB)
- ✅ Tự động tạo UUID cho tên file
- ✅ Lưu trữ ảnh phụ dưới dạng JSON array
- ✅ Xóa ảnh cũ khi cập nhật

## URL truy cập ảnh
- Ảnh chính: `http://localhost:8080/images/uploads/[filename]`
- Ảnh phụ: Lưu trong database dưới dạng JSON array

## Cách sử dụng Admin Panel
1. Truy cập: `http://localhost:8080/admin/products/add`
2. Điền thông tin sản phẩm
3. Upload ảnh chính (bắt buộc)
4. Upload nhiều ảnh phụ (tùy chọn)
5. Nhấn "Thêm sản phẩm"

## Lợi ích so với Cloudinary
- ✅ Không cần API key
- ✅ Không có giới hạn bandwidth
- ✅ Tốc độ nhanh hơn
- ✅ Dễ dàng backup
- ✅ Kiểm soát hoàn toàn dữ liệu

## Lưu ý
- Ảnh được lưu trong thư mục `static/images/uploads/`
- Cần backup thư mục này khi deploy production
- Có thể cấu hình CDN sau này nếu cần

