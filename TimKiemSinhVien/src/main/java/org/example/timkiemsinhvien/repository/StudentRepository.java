package org.example.timkiemsinhvien.repository;

import org.example.timkiemsinhvien.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("SELECT s FROM Student s WHERE " +
            "(:id IS NOT NULL AND s.id = :id) OR " +
            "(:name IS NOT NULL AND s.name LIKE %:name%) OR " +
            "(:dept IS NOT NULL AND s.department LIKE %:dept%)")
    List<Student> searchStudents(@Param("id") String id,
                                 @Param("name") String name,
                                 @Param("dept") String dept);
}
