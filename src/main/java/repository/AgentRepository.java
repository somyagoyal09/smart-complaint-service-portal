package com.miet.complaintportal.repository;

import com.miet.complaintportal.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
}
