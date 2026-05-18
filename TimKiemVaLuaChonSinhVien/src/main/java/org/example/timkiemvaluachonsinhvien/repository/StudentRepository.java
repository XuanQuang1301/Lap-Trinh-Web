package org.example.timkiemvaluachonsinhvien.repository;

import org.example.timkiemvaluachonsinhvien.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("SELECT s FROM Student s WHERE " +
            "(:id = '' OR s.id = :id) AND " +
            "(:name = '' OR s.name LIKE %:name%) AND " +
            "(:department = '' OR s.department = :department) AND " +
            "(:dob IS NULL OR s.dob = :dob)")
    List<Student> searchStudents(
            @Param("id") String id,
            @Param("name") String name,
            @Param("department") String department,
            @Param("dob") LocalDate dob);
}
