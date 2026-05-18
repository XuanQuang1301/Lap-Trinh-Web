package org.example.timkiemvaluachonsinhvien.control;

import jakarta.servlet.http.HttpSession;
import org.example.timkiemvaluachonsinhvien.entity.Student;
import org.example.timkiemvaluachonsinhvien.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @GetMapping("/")
    public String index(Model model, HttpSession session){
        model.addAttribute("id", session.getAttribute("lastId"));
        model.addAttribute("name", session.getAttribute("lastName"));
        model.addAttribute("dob", session.getAttribute("lastDob"));
        model.addAttribute("department", session.getAttribute("lastDepartment"));
        return "index";
    }
    @PostMapping("/search")
    public String searchStudent(@RequestParam String id,
                                @RequestParam String name,
                                @RequestParam(defaultValue = "") String dobStr,
                                @RequestParam String department,
                                Model model,
                                HttpSession session){
        session.setAttribute("lastId", id);
        session.setAttribute("lastName", name);
        session.setAttribute("lastDob", dobStr);
        session.setAttribute("lastDepartment", department);
        LocalDate dob = null;
        if(!dobStr.trim().isEmpty()){
            dob = LocalDate.parse(dobStr);
        }
        List<Student> student = studentRepository.searchStudents(id, name, department, dob);
        model.addAttribute("student", student);
        return "result";
    }
    @PostMapping("/select")
    public String selectStudent(@RequestParam String id, Model model, HttpSession session){
        Student student = studentRepository.findById(id).orElse(null);
        if(student != null){
            student.setSelected(1);
            studentRepository.save(student);
        }
        String lastId = (String) session.getAttribute("lastId");
        String lastName = (String) session.getAttribute("lastName");
        String lastDobStr = (String) session.getAttribute("lastDob");
        String lastDepartment = (String) session.getAttribute("lastDepartment");
        LocalDate dob = (lastDobStr != null && !lastDobStr.isEmpty()) ? LocalDate.parse(lastDobStr) : null;
        List<Student> result = studentRepository.searchStudents(lastId, lastName, lastDepartment, dob);
        model.addAttribute("student", result);
        return "result";
    }
}
