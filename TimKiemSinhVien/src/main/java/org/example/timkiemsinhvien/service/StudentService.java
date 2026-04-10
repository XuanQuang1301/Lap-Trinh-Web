package org.example.timkiemsinhvien.service;

import org.example.timkiemsinhvien.entity.Student;
import org.example.timkiemsinhvien.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public List<Student> findStudent(String id, String name, String dept){
        String searchId = (id == null || id.trim().isEmpty()) ? null : id.trim();
        String searchName = (name == null || name.trim().isEmpty()) ? null : name.trim();
        String searchDept = (dept == null || dept.trim().isEmpty()) ? null : dept.trim();
        return studentRepository.searchStudents(searchId, searchName, searchDept);
    }
    public void selectStudent(String id){
        Student student = studentRepository.findById(id).orElse(null);
        if(student != null){
            student.setSelected(1);
            studentRepository.save(student);
        }
    }
}
