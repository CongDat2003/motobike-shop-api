package PRM392.motobike_shop_api.service;

import PRM392.motobike_shop_api.dto.LoginRequest;
import PRM392.motobike_shop_api.dto.RegisterRequest;
import PRM392.motobike_shop_api.entity.User;
import PRM392.motobike_shop_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public User register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        
        return userRepository.save(user);
    }
    
    public User login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid username or password");
        }
        
        User user = userOptional.get();
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        
        return user;
    }
    
    public void forgotPassword(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Email not found");
        }
        
        // TODO: Implement email sending logic
        // For now, just return success
    }
}









