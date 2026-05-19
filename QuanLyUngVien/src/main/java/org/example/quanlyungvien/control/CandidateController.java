package org.example.quanlyungvien.control;

import jakarta.servlet.http.HttpSession;
import org.example.quanlyungvien.entity.Candidate;
import org.example.quanlyungvien.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;

    public final String [] POSITION = {
            "frontend", "backend", "mobile", "tester"
    };
    @GetMapping("/")
    public String showForm(Model model, HttpSession session){
        model.addAttribute("position", POSITION);
        model.addAttribute("lastPosition", session.getAttribute("lastPosition"));
        return "index";
    }
    @PostMapping("/submit")
    public String processForm(@RequestParam String id,
                              @RequestParam String name,
                              @RequestParam String position,
                              @RequestParam String expYearStr,
                              Model model,
                              HttpSession session){
        boolean hasError = false;
        session.setAttribute("lastPosition", position);
        if(id.trim().isEmpty()) {
            model.addAttribute("errorId", "khong duoc de Id trong");
            hasError = true;
        }
        else if(candidateRepository.existsById(id.trim())) {
            model.addAttribute("errorId", "Id nay da ton tai");
            hasError = true;
        }
        if (name.trim().isEmpty()) {
            model.addAttribute("errorName", "Ten khong duoc de trong");
            hasError = true;
        }
        if(position.trim().isEmpty()) {
            model.addAttribute("errorPosition", "chon vi tri");
            hasError = true;
        }
        Integer expYear = null;
        if(expYearStr.trim().isEmpty()){
            model.addAttribute("errorExp", "khong de trong khinh nghiem");
            hasError =true;
        }else {
            try{
                expYear = Integer.parseInt(expYearStr);
            }catch(NumberFormatException e){
                model.addAttribute("errorExp", "kinh nghiem phai la so");
                hasError = true;
            }
        }
        if(hasError){
            model.addAttribute("position", POSITION);
            model.addAttribute("id", id.trim());
            model.addAttribute("name", name.trim());
            model.addAttribute("expYearStr", expYearStr);
            return "index";
        }
        model.addAttribute("id", id.trim());
        model.addAttribute("name", name.trim());
        model.addAttribute("position", position.trim());
        model.addAttribute("expYear", expYear);
        return "confirm";
    }
    @PostMapping("/confirm")
    public String confirmSave(@RequestParam String id,
                              @RequestParam String name,
                              @RequestParam String position,
                              @RequestParam Integer expYear){
        Candidate candidate = new Candidate();
        candidate.setId(id);
        candidate.setName(name);
        candidate.setPosition(position);
        candidate.setExpYear(expYear);
        candidate.setStatus(0);
        candidateRepository.save(candidate);
        return "redirect:/";
    }
    @GetMapping("/list-pending")
    public String viewPending(Model model){
        List<Candidate> candidates  = candidateRepository.findByStatus(0);
        model.addAttribute("candidates", candidates);
        return "list";
    }
    @PostMapping("/pass")
    public String passCandidate(@RequestParam String id){
        Candidate candidate = candidateRepository.findById(id).orElse(null);
        if(candidate != null){
            candidate.setStatus(1);
            candidateRepository.save(candidate);
        }
        return "redirect:/list-pending";
    }
    @PostMapping("/fail")
    public String failCandidate(@RequestParam String id){
        candidateRepository.deleteById(id);
        return "redirect:/list-pending";
    }
}
