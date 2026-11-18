package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

        @Autowired
        private UserService userService;

        @PostMapping("/save")
        public User save(@RequestBody User user) {
                return userService.saveUser(user);
        }

        @GetMapping("/{id}")
        public User get(@PathVariable Long id) {
                return userService.getUser(id);
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
                userService.deleteUser(id);
        }

}
