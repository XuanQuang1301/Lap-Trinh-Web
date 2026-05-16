package org.example.timkiemvaluachonsinhvien.repository;

import org.example.timkiemvaluachonsinhvien.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("SELECT s FROM Student s WHERE " +
            "(:id IS NOT NULL AND s.id = :id) OR " +
            "(:name IS NOT NULL AND s.name LIKE CONCAT('%', :name, '%')) OR " +
            "(:dob IS NOT NULL AND s.dob = :dob) OR " +
            "(:department IS NOT NULL AND s.department = :department)")
    List<Student> searchStudents(String id, String name, LocalDate dob, String department);
}
