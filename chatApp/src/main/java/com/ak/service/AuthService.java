package com.ak.service;

import com.ak.entity.User;
import com.ak.repository.UserRepository;
import com.ak.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // Hash password using SHA-256 instead of BCrypt
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public boolean register(User user) {
        // Check if username already exists
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return false;
        }

        // Encrypt password and save user
        user.setPassword(hashPassword(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public String login(User user) {
        // Check if user exists
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent() && 
            hashPassword(user.getPassword()).equals(existingUser.get().getPassword())) {
            // Generate and return a token
            return TokenUtils.generateToken(existingUser.get().getUsername());
        }
        return null;
    }

    public boolean validateToken(String token) {
        String username = TokenUtils.getUsernameFromToken(token);
        return username != null && userRepository.findByUsername(username).isPresent();
    }

    public User getUserByToken(String token) {
        String username = TokenUtils.getUsernameFromToken(token);
        return userRepository.findByUsername(username).orElse(null);
    }
}
