package org.example.de_7_2.control;


import org.example.de_7_2.entity.Student;
import org.example.de_7_2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@CrossOrigin("*")
public class StudentController {
    @Autowired
    private StudentService studentService;
    // Nhan nut Add
    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestBody Student student){
        if(student.getId().isEmpty() || student.getName().isEmpty() || student.getDob() == null){
            return ResponseEntity.badRequest().body("Vui lòng nhập đầy đủ thông tin");
        }
        if(studentService.existsById(student.getId())){
            return ResponseEntity.badRequest().body("ID da ton tai trong he thong");
        }
        return ResponseEntity.ok(student);
    }
    // Nhan nut confirm
    @PostMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestBody Student student){
        try {
            studentService.saveStudent(student);
            return ResponseEntity.ok("Them sinh vien thanh cong ");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Loi khi luu du lieu");
        }
    }
}
