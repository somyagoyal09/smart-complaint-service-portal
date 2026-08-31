package com.miet.complaintportal.repository;

import com.miet.complaintportal.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}