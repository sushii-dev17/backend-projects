package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repostory.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Cache user by ID
    @Cacheable(value = "users", key = "#id")
    public User getUser(Long id) {
        System.out.println("Fetching from DB...");
        return userRepository.findById(id).orElse(null);
    }

    // Update cache when saving
    @CachePut(value = "users", key = "#result.id")
    public User saveUser(User user) {
        System.out.println("Saving to DB...");
        return userRepository.save(user);
    }

    // Remove cache on delete
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        System.out.println("Deleted from DB & Cache");
    }
}
