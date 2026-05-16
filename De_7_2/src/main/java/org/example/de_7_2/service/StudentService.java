package org.example.de_7_2.service;

import org.example.de_7_2.entity.Student;

public interface StudentService {
    boolean existsById(String id);
    void saveStudent(Student student);
}
