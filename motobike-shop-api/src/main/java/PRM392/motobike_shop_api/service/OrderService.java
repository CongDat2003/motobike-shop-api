package PRM392.motobike_shop_api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import PRM392.motobike_shop_api.entity.Order;
import PRM392.motobike_shop_api.entity.OrderItem;
import PRM392.motobike_shop_api.entity.Product;
import PRM392.motobike_shop_api.repository.OrderItemRepository;
import PRM392.motobike_shop_api.repository.OrderRepository;
import PRM392.motobike_shop_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    
    public List<Order> getAllOrders() {
        return orderRepository.findAllOrderByCreatedAtDesc();
    }
    
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
    
    public List<Order> getOrdersByStatus(Order.OrderStatus status) {
        return orderRepository.findByStatus(status);
    }
    
    public List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByCreatedAtBetween(startDate, endDate);
    }
    
    public List<Order> searchOrders(String keyword) {
        return orderRepository.findByOrderNumberContaining(keyword);
    }
    
    public Long getOrderCountByStatus(Order.OrderStatus status) {
        return orderRepository.countByStatus(status);
    }
    
    public Double getTotalRevenueByStatusAndDateRange(Order.OrderStatus status, 
                                                    LocalDateTime startDate, 
                                                    LocalDateTime endDate) {
        Double revenue = orderRepository.getTotalRevenueByStatusAndDateRange(status, startDate, endDate);
        return revenue != null ? revenue : 0.0;
    }
    
    @Transactional
    public Order updateOrderStatus(Long orderId, Order.OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }
    
    @Transactional
    public Order createOrder(Order order) {
        // Calculate total amount
        double totalAmount = order.getOrderItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getUnitPrice())
                .sum();
        order.setTotalAmount(totalAmount);
        
        // Update product stock
        for (OrderItem item : order.getOrderItems()) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            
            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }
            
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
        }
        
        return orderRepository.save(order);
    }
    
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        
        if (order.getStatus() == Order.OrderStatus.DELIVERED) {
            throw new RuntimeException("Cannot cancel delivered order");
        }
        
        // Restore product stock
        for (OrderItem item : order.getOrderItems()) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            
            product.setStock(product.getStock() + item.getQuantity());
            productRepository.save(product);
        }
        
        order.setStatus(Order.OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
    
    // Auto-approve orders with payment status "PAID"
    @Transactional
    public void autoApprovePaidOrders() {
        List<Order> paidOrders = orderRepository.findAll().stream()
                .filter(order -> "PAID".equals(order.getPaymentStatus()) && 
                                order.getStatus() == Order.OrderStatus.PENDING)
                .toList();
        
        for (Order order : paidOrders) {
            order.setStatus(Order.OrderStatus.CONFIRMED);
            orderRepository.save(order);
        }
    }
}


