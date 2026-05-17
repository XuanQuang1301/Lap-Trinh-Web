package org.example.de7nhapsinhvien.repository;

import org.example.de7nhapsinhvien.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
    boolean existsById(String id);
}
