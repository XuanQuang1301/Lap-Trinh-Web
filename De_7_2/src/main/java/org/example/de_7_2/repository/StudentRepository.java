package org.example.de_7_2.repository;

import org.example.de_7_2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  StudentRepository extends JpaRepository<Student, String> {

}
