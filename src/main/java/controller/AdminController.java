package com.miet.complaintportal.controller;

import com.miet.complaintportal.entity.Complaint;
import com.miet.complaintportal.entity.User;
import com.miet.complaintportal.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // ---- User Management ----
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
    }

    // ---- Complaint Assignment ----
    @PutMapping("/complaints/{complaintId}/assign/{agentId}")
    public Complaint assignComplaint(@PathVariable Long complaintId, @PathVariable Long agentId) {
        return adminService.assignComplaintToAgent(complaintId, agentId);
    }

    // ---- Dashboard Analytics ----
    @GetMapping("/dashboard")
    public Map<String, Object> getDashboardStats() {
        return adminService.getDashboardStats();
    }
}