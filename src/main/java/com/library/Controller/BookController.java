package com.library.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.Service.BookService;
import com.library.entity.Book;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    // Single book add
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    // Multiple books add
    @PostMapping("/bulk")
    public List<Book> addBooks(@RequestBody List<Book> books) {
        return service.saveAll(books);
    }

    // Get all books
    @GetMapping
    public List<Book> getAll() {
        return service.getAllBooks();
    }
    
    //update book by id
    @PutMapping("/{id}") 
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return service.updateBook(id, book);
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
        return "Book deleted successfully";
    }
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return service.getBookById(id);
    }
   
    @PutMapping("/return/{id}")
    public Book returnBook(@PathVariable Long id) {
        return service.returnBook(id);
    }
}