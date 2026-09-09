package com.miet.complaintportal.controller;

import jakarta.validation.Valid;
import com.miet.complaintportal.entity.User;
import com.miet.complaintportal.service.UserService;
import com.miet.complaintportal.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public User createUser( @Valid @RequestBody User user) {
        return userService.createUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        User loggedInUser = userService.loginUser(user.getEmail(), user.getPassword());

        if (loggedInUser == null) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        String token = jwtUtil.generateToken(loggedInUser.getEmail(), loggedInUser.getRole());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", loggedInUser);

        return ResponseEntity.ok(response);
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}