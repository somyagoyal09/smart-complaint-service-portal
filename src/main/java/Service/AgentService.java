package com.miet.complaintportal.service;

import com.miet.complaintportal.entity.Agent;
import com.miet.complaintportal.entity.Complaint;
import com.miet.complaintportal.repository.AgentRepository;
import com.miet.complaintportal.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private ComplaintRepository complaintRepository;

    public Agent createAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    public Agent getAgentById(Long id) {
        return agentRepository.findById(id).orElse(null);
    }

    public void deleteAgent(Long id) {
        agentRepository.deleteById(id);
    }

    // ---- Agent Dashboard: find agent record using logged-in user's email ----
    public Agent getAgentByEmail(String email) {
        return agentRepository.findByEmail(email);
    }

    // ---- Agent Dashboard: complaints assigned to this agent ----
    public List<Complaint> getComplaintsByAgentId(Long agentId) {
        return complaintRepository.findByAgentId(agentId);
    }

    // ---- Agent updates complaint status ----
    public Complaint updateComplaintStatus(Long complaintId, String status) {
        Complaint complaint = complaintRepository.findById(complaintId).orElse(null);
        if (complaint == null) {
            return null;
        }
        complaint.setStatus(status);
        return complaintRepository.save(complaint);
    }

    // ---- Agent adds resolution remarks ----
    public Complaint addRemarks(Long complaintId, String remarks) {
        Complaint complaint = complaintRepository.findById(complaintId).orElse(null);
        if (complaint == null) {
            return null;
        }
        complaint.setRemarks(remarks);
        return complaintRepository.save(complaint);
    }

    // ---- Agent closes complaint ----
    public Complaint closeComplaint(Long complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId).orElse(null);
        if (complaint == null) {
            return null;
        }
        complaint.setStatus("CLOSED");
        return complaintRepository.save(complaint);
    }
}