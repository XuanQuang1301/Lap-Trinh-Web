package org.example.quanlymuontrasach.control;

import jakarta.servlet.http.HttpSession;
import org.example.quanlymuontrasach.entity.Book;
import org.example.quanlymuontrasach.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class BookController {
    @Autowired
    private BookRepository bookRepository;
    private final String [] CATEGORY = {"IT", "Van hoc", "Kinh te", "Toan hoc"};
    @GetMapping("/")
    public String index(HttpSession session, Model model){
        String searchCategory = (String) session.getAttribute("searchCategory");
        if(searchCategory == null){
            searchCategory = "";
        }
        List<Book> books = bookRepository.searchByCategory(searchCategory);
        model.addAttribute("books", books);
        model.addAttribute("searchCategory", searchCategory);
        return "index";
    }
    @PostMapping("/search")
    public String search(@RequestParam(defaultValue = "") String category, HttpSession session){
        session.setAttribute("searchCategory", category.trim());
        return "redirect:/";
    }
    @PostMapping("/borrow")
    public String borrowBook(@RequestParam String id){
        Book book = bookRepository.findById(id).orElse(null);
        if(book != null){
            book.setStatus(1);
            bookRepository.save(book);
        }
        return "redirect:/";
    }
    @PostMapping("/return")
    public String returnBook(@RequestParam String id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setStatus(0); // Đổi thành Có sẵn
            bookRepository.save(book);
        }
        return "redirect:/";
    }
    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("categories", CATEGORY);
        return "add";
    }
    @PostMapping("/add-submit")
    public String addBook(@RequestParam String id,
                          @RequestParam String title,
                          @RequestParam String author,
                          @RequestParam String category,
                          Model model
                          ){
        boolean hasError = false;
        if(id.trim().isEmpty()){
            model.addAttribute("errorId", "Id khong duoc de trong");
            hasError = true;
        }else if(bookRepository.existsById(id.trim())){
            model.addAttribute("errorId", "Id nay da ton tai");
            hasError = true;
        }
        if(title.trim().isEmpty()){
            model.addAttribute("errorTitle", "Title khong duoc de trong");
            hasError = true;
        }
        if(author.trim().isEmpty()){
            model.addAttribute("errorAuthor", "Author khong duoc de trong");
            hasError = true;
        }
        if(category.trim().isEmpty()){
            model.addAttribute("errorCategory", "Category khong duoc de trong");
            hasError = true;
        }
        if(hasError){
            model.addAttribute("categories", CATEGORY);
            model.addAttribute("id", id);
            model.addAttribute("title", title);
            model.addAttribute("author", author);
            model.addAttribute("lastCategory", category);
            return "add";
        }
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);
        book.setStatus(0);
        bookRepository.save(book);
        return "redirect:/";
    }
}
