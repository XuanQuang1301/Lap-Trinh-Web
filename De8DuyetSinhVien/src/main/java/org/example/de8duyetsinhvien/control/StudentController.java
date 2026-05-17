package org.example.de8duyetsinhvien.control;

import org.example.de8duyetsinhvien.entity.Student;
import org.example.de8duyetsinhvien.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/student")
    public String viewStudent(Model model){
        List<Student> unapprovedStudent = studentRepository.findByApproved(0);
        model.addAttribute("student", unapprovedStudent);
        return "list";
    }
    @PostMapping("/approve")
    public String approveStudent(@RequestParam String id){
        Student student = studentRepository.findById(id).orElse(null);
        if(student != null){
            student.setApproved(1);
            studentRepository.save(student);
        }
        return "redirect:/student";
    }
    @PostMapping("/delete-request")
    public String requestDelete(@RequestParam String id, Model model){
        Student student = studentRepository.findById(id).orElse(null);
        if(student != null){
            model.addAttribute("student", student);
            return "delete-confirm";
        }
        return "redirect:/student";
    }
    @PostMapping("/delete-confirm")
    public String confirmDelete(@RequestParam String id){
        studentRepository.deleteById(id);
        return "redirect:/student";
    }
}
