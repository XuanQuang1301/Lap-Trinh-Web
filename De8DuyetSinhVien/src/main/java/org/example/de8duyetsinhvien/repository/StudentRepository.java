package org.example.de8duyetsinhvien.repository;

import org.example.de8duyetsinhvien.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findByApproved(Integer approved);
}
