package org.example.timkiemvaluachonsinhvien.controller;

import org.example.timkiemvaluachonsinhvien.entity.Student;
import org.example.timkiemvaluachonsinhvien.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin("*")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody Student req){
        String id = (req.getId() != null && !req.getId().isEmpty()) ? req.getId() : null;
        String name = (req.getName() != null && !req.getName().isEmpty()) ? req.getName() : null;
        String dept = (req.getDepartment() != null && !req.getDepartment().isEmpty()) ? req.getDepartment() : null;
        LocalDate dob = req.getDob();
        List<Student> result = studentRepository.searchStudents(id, name, dob, dept);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/select/{id}")
    public ResponseEntity<?> selectStudent(@PathVariable String id){
        Student student = studentRepository.findById(id).orElse(null);
        if(student == null){
            return ResponseEntity.badRequest().body("Khong tim thay sinh vien");
        }
        student.setSelected(1);
        studentRepository.save(student);
        return ResponseEntity.ok("Chon sinh vien thanh cong");
    }
}
