package com.library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.Service.BookService;
import com.library.Service.IssueService;
import com.library.Service.UserService;
import com.library.entity.Book;
import com.library.entity.Issue;
import com.library.entity.User;

@Controller
public class HomeController {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private IssueService issueService;

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/books-ui")
    public String booksPage(Model model) {
    	model.addAttribute("books", bookService.getAllBooks());
        return "Book";
    }
    

    @GetMapping("/users-ui")
    public String usersPage(Model model) {
    	model.addAttribute("users", userService.getAllUsers());
        return "Users";
    }
    
    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users-ui";
    }

    @GetMapping("/issues-ui")
    public String issuesPage(Model model) {
    	model.addAttribute("issues", issueService.getAllIssues());
        return "Issues";
    }

    @GetMapping("/add-user")
    public String addUserPage() {
        return "add-user";
    }
    
    @PostMapping("/add-user")
    public String saveUser(User user) {
    	
    	System.out.println("Name= " +user.getName());
        System.out.println("Email= " +user.getEmail());
        System.out.println("phone= " +user.getPhone());
    	userService.addUser(user);
    	return "redirect:/users-ui";
    }

    @GetMapping("/issue-book")
    public String issueBookPage() {
        return "issue-book";
    }
    
    @PostMapping("/issue-book")
    public String issueBook(Long bookId, Long userId) {
    	issueService.issueBook(bookId,userId);
    	return "redirect:/issues-ui";
    }

    @GetMapping("/return-book/{id}")
    public String returnBook(@PathVariable Long id){
        issueService.returnBook(id);
        return "redirect:/issues-ui";
    }
    
    @GetMapping("/add-book")
    public String addBookPage() {
        return "add-book";
    }

    @PostMapping("/add-book")
    public String saveBook(Book book) {

        bookService.addBook(book);

        return "redirect:/books-ui";
    }
    
    @GetMapping("/search-book")
    public String searchBook(@RequestParam String keyword,
                             Model model) {

        model.addAttribute("books",
                bookService.SearchBook(keyword));

        return "Book";
    }
}