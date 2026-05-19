package org.example.quanlyungvien.repository;

import org.example.quanlyungvien.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, String> {
    boolean existsById(String id);
    List<Candidate> findByStatus(Integer status);
}
