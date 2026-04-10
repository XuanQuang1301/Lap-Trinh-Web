package org.example.test_de7.Controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.test_de7.model.Student;
import org.example.test_de7.respository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@CrossOrigin("*") //Để front gọi vào k bị lỗi

public class StudentController {
    @Autowired
    private StudentRepository repository;
    //Gửi
    @PostMapping("/preview")
    public ResponseEntity<?> preview(@Valid @RequestBody Student student, HttpSession session){
        if(repository.existsById(student.getId())){
            return ResponseEntity.badRequest().body("ID đã tồn tại trong hệ thống");
        }
        session.setAttribute(   "tempStudent", student);
        session.setAttribute("lastDept", student.getDepartment());
        return ResponseEntity.ok(student);
    }
    @PostMapping("/confirm")
    public ResponseEntity<?> confirm(HttpSession session){
        Student student = (Student) session.getAttribute("tempStudent");
        if(student == null){
            return ResponseEntity.badRequest().body("Không tìm thấy dữ liệu xác nhận");
        }
        repository.save(student);
        session.removeAttribute("tempStudent");
        return ResponseEntity.ok("Thêm sinh viên thành công");
    }
    @PostMapping("/last-dept")
    public String getLastDept(HttpSession session){
        Object dept = session.getAttribute("lastDept");
        return dept != null ? dept.toString() : "";
    }
}
