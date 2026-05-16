package org.example.th2nhapsach.control;

import jakarta.servlet.http.HttpSession;
import org.example.th2nhapsach.entity.Book;
import org.example.th2nhapsach.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    private final String [] CATEGORIES = {
            "Văn học", "Khoa Học", "Công nghệ", "Kinh tế"
    };
    @GetMapping("/")
    public String showDown (Model model, HttpSession session){
        model.addAttribute("categories", CATEGORIES);
        String lastCategory = (String) session.getAttribute("lastCategory");
        model.addAttribute("lastCategory", lastCategory);
        return "form";
    }
    @PostMapping("/add")
    public String addBook(@RequestParam String bookCode,
                          @RequestParam String title,
                          @RequestParam String author,
                          @RequestParam String category,
                          HttpSession session,
                          Model model){
        boolean hasError = false;
        if(bookCode == null || bookCode.trim().isEmpty()){
            model.addAttribute("errorBookCode", "Ten sach khong duoc de trong");
            hasError = true;
        }
        if (title == null || title.trim().isEmpty()) {
            model.addAttribute("errorTitle", "Tên sách không được để trống");
            hasError = true;
        }
        if (author == null || author.trim().isEmpty()) {
            model.addAttribute("errorAuthor", "Tác giả không được để trống");
            hasError = true;
        }
        if (category == null || category.trim().isEmpty()) {
            model.addAttribute("errorCategory", "Thể loại không được để trống");
            hasError = true;
        }
        if(!hasError || (bookCode != null && !bookCode.trim().isEmpty())){
            if(bookCode != null && !bookCode.trim().isEmpty()){
                Book existing = bookRepository.findByBookCode(bookCode);
                if(existing != null){
                    model.addAttribute("errorBookCode", "BookCode" + bookCode + " Da ton tai");
                    hasError = true;
                }
            }
        }
        if(hasError){
            model.addAttribute("categories", CATEGORIES);
            model.addAttribute("lastCategory", category);
            model.addAttribute("bookCode", bookCode);
            model.addAttribute("title", title);
            model.addAttribute("author", author);
            session.setAttribute("lastCategory", category);
            return "form";
        }
        session.setAttribute("lastCategory", category);
        model.addAttribute("bookCode", bookCode.trim());
        model.addAttribute("title", title.trim());
        model.addAttribute("author", author.trim());
        model.addAttribute("category", category);
        return "confirm";
    }
    @PostMapping("/confirm")
    public String confirmBook(@RequestParam String bookCode,
                              @RequestParam String title,
                              @RequestParam String author,
                              @RequestParam String category){
        Book book = new Book();
        book.setBookCode(bookCode);
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);
        bookRepository.save(book);
        return "redirect:/";
    }
}
