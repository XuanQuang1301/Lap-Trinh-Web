package org.example.timkiemsinhvien.controller;

import jakarta.servlet.http.HttpSession;
import org.example.timkiemsinhvien.entity.Student;
import org.example.timkiemsinhvien.service.StudentService;
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
    private StudentService studentService;
    @GetMapping(value = {"/", "/index"})
    public String index(HttpSession session, Model model) {
        model.addAttribute("oldId", session.getAttribute("sessId"));
        model.addAttribute("oldName", session.getAttribute("sessName"));
        model.addAttribute("oldDept", session.getAttribute("sessDept"));
        return "index";
    }
    @PostMapping("/search")
    public String search(@RequestParam String id, @RequestParam String name,
                         @RequestParam String department, HttpSession session, Model model) {
        session.setAttribute("sessId", id);
        session.setAttribute("sessName", name);
        session.setAttribute("sessDept", department);
        List<Student> list = studentService.findStudent(id, name, department);

        model.addAttribute("students", list);
        return "result";
    }
    @PostMapping("/select")
    public String selectStudent(@RequestParam("id") String id, HttpSession session, Model model){
        studentService.selectStudent(id);
        String sId = (String) session.getAttribute("sessId");
        String sName = (String) session.getAttribute("sessName");
        String sDept = (String) session.getAttribute("sessDept");
        List<Student> list  = studentService.findStudent(sId, sName, sDept);
        return "result";
    }
}
