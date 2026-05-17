package org.example.de7nhapsinhvien.control;
import java.time.format.DateTimeFormatter;
import jakarta.servlet.http.HttpSession;
import org.example.de7nhapsinhvien.entity.Student;
import org.example.de7nhapsinhvien.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@CrossOrigin("*")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    private final String [] DEPARTMENT = {
        "CNTT", "ATTT", "CNPM", "HTTT"
    };
    @GetMapping("/")
    public String showDown(Model model, HttpSession session){
        model.addAttribute("department", DEPARTMENT);
        String lastDepartment = (String) session.getAttribute("lastDepartment");
        model.addAttribute("lastDepartment", lastDepartment);
        return "form";
    }
    @PostMapping("/add")
    public String addStudent(@RequestParam String id,
                             @RequestParam String name,
                             @RequestParam String dob,
                             @RequestParam String department,
                             Model model,
                             HttpSession session){
        boolean hasError = false;

        if(id == null || id.trim().isEmpty()){
            model.addAttribute("errorId", "Id khong duoc de trong");
            hasError = true;
        }
        if(name == null || name.trim().isEmpty()){
            model.addAttribute("errorName", "Name khong duoc de trong");
            hasError = true;
        }
        if(dob == null || dob.trim().isEmpty()){
            model.addAttribute("errorDob", "Dob khong duoc de trong");
            hasError = true;
        }
        if(department == null || department.trim().isEmpty()){
            model.addAttribute("errorDepartment", "Department khong duoc de trong");
            hasError = true;
        }
        if (!id.trim().isEmpty() && studentRepository.existsById(id.trim())) {
            model.addAttribute("errorId", "ID này đã tồn tại trong hệ thống!");
            hasError = true;
        }
        if(hasError){
            model.addAttribute("department", DEPARTMENT);
            model.addAttribute("lastDepartment", department);
            model.addAttribute("id", id);
            model.addAttribute("name", name);
            model.addAttribute("dob", dob);
            session.setAttribute("lastDepartment", department);
            return "form";
        }
        session.setAttribute("lastDepartment", department);
        model.addAttribute("id", id.trim());
        model.addAttribute("name", name.trim());
        model.addAttribute("dob", dob);
        model.addAttribute("department", department);
        return "confirm";
    }
    @PostMapping("/confirm")
    public String confirmSave(@RequestParam String id,
                              @RequestParam String name,
                              @RequestParam String dob,
                              @RequestParam String department){
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        student.setDob(LocalDate.parse(dob, formatter));
        student.setDepartment(department);
        student.setApproved(0);
        studentRepository.save(student);
        return "redirect:/";
    }
}
