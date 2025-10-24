package PRM392.motobike_shop_api.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import PRM392.motobike_shop_api.entity.Order;
import PRM392.motobike_shop_api.service.OrderService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {
    
    private final OrderService orderService;
    
    @GetMapping
    public String orders(@RequestParam(value = "status", required = false) String status,
                        @RequestParam(value = "search", required = false) String search,
                        Model model) {
        List<Order> orders;
        
        if (search != null && !search.trim().isEmpty()) {
            orders = orderService.searchOrders(search.trim());
        } else if (status != null && !status.trim().isEmpty()) {
            try {
                Order.OrderStatus orderStatus = Order.OrderStatus.valueOf(status.toUpperCase());
                orders = orderService.getOrdersByStatus(orderStatus);
            } catch (IllegalArgumentException e) {
                orders = orderService.getAllOrders();
            }
        } else {
            orders = orderService.getAllOrders();
        }
        
        model.addAttribute("orders", orders);
        model.addAttribute("status", status);
        model.addAttribute("search", search);
        model.addAttribute("orderStatuses", Order.OrderStatus.values());
        
        return "admin/orders";
    }
    
    @GetMapping("/{id}")
    public String orderDetail(@PathVariable Long id, Model model) {
        Order order = orderService.getOrderById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        
        model.addAttribute("order", order);
        return "admin/order-detail";
    }
    
    @PostMapping("/{id}/update-status")
    public String updateOrderStatus(@PathVariable Long id, 
                                   @RequestParam("status") String status) {
        try {
            Order.OrderStatus newStatus = Order.OrderStatus.valueOf(status.toUpperCase());
            orderService.updateOrderStatus(id, newStatus);
            return "redirect:/admin/orders/" + id + "?success=Trạng thái đơn hàng đã được cập nhật";
        } catch (Exception e) {
            return "redirect:/admin/orders/" + id + "?error=Lỗi cập nhật: " + e.getMessage();
        }
    }
    
    @PostMapping("/{id}/cancel")
    public String cancelOrder(@PathVariable Long id) {
        try {
            orderService.cancelOrder(id);
            return "redirect:/admin/orders?success=Đơn hàng đã được hủy";
        } catch (Exception e) {
            return "redirect:/admin/orders?error=Lỗi hủy đơn hàng: " + e.getMessage();
        }
    }
    
    @PostMapping("/auto-approve")
    public String autoApprovePaidOrders() {
        try {
            orderService.autoApprovePaidOrders();
            return "redirect:/admin/orders?success=Đã tự động duyệt các đơn hàng đã thanh toán";
        } catch (Exception e) {
            return "redirect:/admin/orders?error=Lỗi tự động duyệt: " + e.getMessage();
        }
    }
}


