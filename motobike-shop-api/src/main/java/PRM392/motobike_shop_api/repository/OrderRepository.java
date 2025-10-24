package PRM392.motobike_shop_api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import PRM392.motobike_shop_api.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByStatus(Order.OrderStatus status);
    
    List<Order> findByUserId(Long userId);
    
    List<Order> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT o FROM Order o WHERE o.orderNumber LIKE %:orderNumber%")
    List<Order> findByOrderNumberContaining(@Param("orderNumber") String orderNumber);
    
    @Query("SELECT o FROM Order o WHERE o.user.email LIKE %:email%")
    List<Order> findByUserEmailContaining(@Param("email") String email);
    
    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = :status")
    Long countByStatus(@Param("status") Order.OrderStatus status);
    
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.status = :status AND o.createdAt BETWEEN :startDate AND :endDate")
    Double getTotalRevenueByStatusAndDateRange(@Param("status") Order.OrderStatus status, 
                                             @Param("startDate") LocalDateTime startDate, 
                                             @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT o FROM Order o ORDER BY o.createdAt DESC")
    List<Order> findAllOrderByCreatedAtDesc();
}


