package com.library.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.Repository.BookRepository;
import com.library.entity.Book;
import com.library.exception.BookNotFoundException;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repo;

    // Add Book
    public Book addBook(Book book) {
        return repo.save(book);
    }
    
    //Add list of books
    public List<Book>saveAll(List<Book>books){
    	return repo.saveAll(books);
    }

    // Get All Books
    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    // Get Book by ID
    public Book getBookById(Long id) {
        return repo.findById(id) .orElseThrow(() ->
        new BookNotFoundException(
                "Book not found with id : " + id));
    }

    // Delete Book
//    public void deleteBook(Long id) {
//        repo.deleteById(id);
//    }
    public void deleteBook(Long id) {
        Book book = repo.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
        repo.delete(book);
    }
    
    //update book
    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = repo.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setQuantity(updatedBook.getQuantity());

        return repo.save(existingBook);
    }
    
    public Book returnBook(Long id) {
        Book book = repo.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        book.setQuantity(book.getQuantity() + 1);

        return repo.save(book);
    }
    
    public List<Book>SearchBook(String title){
    	return repo.findByTitleContainingIgnoreCase(title);
    }
    
    public long countBooks() {
    	return repo.count();
    }
}
