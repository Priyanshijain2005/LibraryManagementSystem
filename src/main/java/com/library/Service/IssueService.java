package com.library.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.Repository.BookRepository;
import com.library.Repository.IssueRepository;
import com.library.entity.Book;
import com.library.entity.Issue;

import java.time.LocalDate;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepo;

    @Autowired
    private BookRepository bookRepo;

    // Issue Book
    public String issueBook(Long bookId, Long userId) {

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getQuantity() <= 0) {
            return "Book not available";
        }

        book.setQuantity(book.getQuantity() - 1);
        bookRepo.save(book);

        Issue issue = new Issue();
        issue.setBookId(bookId);
        issue.setUserId(userId);
        issue.setIssueDate(LocalDate.now());
        issue.setReturnDate(LocalDate.now().plusDays(7));

        issueRepo.save(issue);

        return "Book issued successfully";
    }

    // Return Book
    public String returnBook(Long issueId) {

        Issue issue = issueRepo.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue record not found"));

        Book book = bookRepo.findById(issue.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setQuantity(book.getQuantity() + 1);
        bookRepo.save(book);

        issueRepo.delete(issue);

        return "Book returned successfully";
    }
}
