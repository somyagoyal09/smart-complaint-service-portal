package com.miet.complaintportal.controller;

import com.miet.complaintportal.entity.Agent;
import com.miet.complaintportal.entity.Complaint;
import com.miet.complaintportal.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @PostMapping
    public Agent createAgent(@Valid @RequestBody Agent agent) {
        return agentService.createAgent(agent);
    }

    @GetMapping
    public List<Agent> getAllAgents() {
        return agentService.getAllAgents();
    }

    @GetMapping("/{id}")
    public Agent getAgentById(@PathVariable Long id) {
        return agentService.getAgentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAgent(@PathVariable Long id) {
        agentService.deleteAgent(id);
    }

    // ---- Agent Dashboard: complaints assigned to this agent (by email) ----
    @GetMapping("/{email}/complaints")
    public List<Complaint> getMyComplaints(@PathVariable String email) {
        Agent agent = agentService.getAgentByEmail(email);
        if (agent == null) {
            return List.of();
        }
        return agentService.getComplaintsByAgentId(agent.getId());
    }

    // ---- Agent updates complaint status ----
    @PutMapping("/complaints/{complaintId}/status")
    public Complaint updateStatus(@PathVariable Long complaintId, @RequestBody Map<String, String> body) {
        return agentService.updateComplaintStatus(complaintId, body.get("status"));
    }

    // ---- Agent adds resolution remarks ----
    @PutMapping("/complaints/{complaintId}/remarks")
    public Complaint addRemarks(@PathVariable Long complaintId, @RequestBody Map<String, String> body) {
        return agentService.addRemarks(complaintId, body.get("remarks"));
    }

    // ---- Agent closes complaint ----
    @PutMapping("/complaints/{complaintId}/close")
    public Complaint closeComplaint(@PathVariable Long complaintId) {
        return agentService.closeComplaint(complaintId);
    }
}