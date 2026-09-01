package com.miet.complaintportal.service;

import com.miet.complaintportal.entity.Agent;
import com.miet.complaintportal.entity.Complaint;
import com.miet.complaintportal.entity.User;
import com.miet.complaintportal.repository.AgentRepository;
import com.miet.complaintportal.repository.ComplaintRepository;
import com.miet.complaintportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private AgentRepository agentRepository;

    // ---- User Management ----
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    // ---- Complaint Assignment ----
    public Complaint assignComplaintToAgent(Long complaintId, Long agentId) {
        Complaint complaint = complaintRepository.findById(complaintId).orElse(null);
        Agent agent = agentRepository.findById(agentId).orElse(null);

        if (complaint == null || agent == null) {
            return null;
        }

        complaint.setAgent(agent);
        complaint.setStatus("IN_PROGRESS");
        return complaintRepository.save(complaint);
    }

    // ---- Analytics / Dashboard Stats ----
    public Map<String, Object> getDashboardStats() {
        List<Complaint> allComplaints = complaintRepository.findAll();

        long totalComplaints = allComplaints.size();
        long pending = allComplaints.stream().filter(c -> "PENDING".equals(c.getStatus()) || "OPEN".equals(c.getStatus())).count();
        long inProgress = allComplaints.stream().filter(c -> "IN_PROGRESS".equals(c.getStatus())).count();
        long resolved = allComplaints.stream().filter(c -> "RESOLVED".equals(c.getStatus())).count();
        long closed = allComplaints.stream().filter(c -> "CLOSED".equals(c.getStatus())).count();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userRepository.findAll().size());
        stats.put("totalAgents", agentRepository.findAll().size());
        stats.put("totalComplaints", totalComplaints);
        stats.put("pending", pending);
        stats.put("inProgress", inProgress);
        stats.put("resolved", resolved);
        stats.put("closed", closed);

        return stats;
    }
}