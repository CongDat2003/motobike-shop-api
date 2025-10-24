package PRM392.motobike_shop_api.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import PRM392.motobike_shop_api.entity.User;
import PRM392.motobike_shop_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/customers")
@RequiredArgsConstructor
public class AdminCustomerController {
    
    private final UserRepository userRepository;
    
    @GetMapping
    public String customers(@RequestParam(value = "search", required = false) String search,
                          @RequestParam(value = "status", required = false) String status,
                          Model model) {
        List<User> customers;
        
        if (search != null && !search.trim().isEmpty()) {
            customers = userRepository.findByEmailContainingIgnoreCase(search.trim());
        } else {
            customers = userRepository.findAll();
        }
        
        model.addAttribute("customers", customers);
        model.addAttribute("search", search);
        model.addAttribute("status", status);
        
        return "admin/customers";
    }
    
    @GetMapping("/{id}")
    public String customerDetail(@PathVariable Long id, Model model) {
        User customer = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        model.addAttribute("customer", customer);
        return "admin/customer-detail";
    }
}
