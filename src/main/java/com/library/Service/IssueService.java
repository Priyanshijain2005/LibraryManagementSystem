package com.library.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.Repository.BookRepository;
import com.library.Repository.IssueRepository;
import com.library.entity.Book;
import com.library.entity.Issue;
import com.library.exception.BookNotAvailableException;
import com.library.exception.BookNotFoundException;
import com.library.exception.IssueNotFoundException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepo;

    @Autowired
    private BookRepository bookRepo;

    public String issueBook(Long bookId, Long userId) {

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() ->
                        new BookNotFoundException(
                                "Book not found with id : " + bookId));

        if (book.getQuantity() <= 0) {
            throw new BookNotAvailableException(
                    "Book is currently unavailable");
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

    public String returnBook(Long issueId) {

        Issue issue = issueRepo.findById(issueId)
                .orElseThrow(() ->
                        new IssueNotFoundException(
                                "Issue record not found"));

        Book book = bookRepo.findById(issue.getBookId())
                .orElseThrow(() ->
                        new BookNotFoundException(
                                "Book not found"));

        book.setQuantity(book.getQuantity() + 1);
        bookRepo.save(book);
        if(LocalDate.now().isAfter(issue.getReturnDate())){

            long days =
                    ChronoUnit.DAYS.between(
                            issue.getReturnDate(),
                            LocalDate.now());

            issue.setFine(days * 10.0);
        }

        issueRepo.delete(issue);

        return "Book returned successfully";
    }
    
    public List<Issue>getAllIssues(){
    	return issueRepo.findAll();
    }
    
    public Issue getIssueById(Long issueId) {
    	return issueRepo.findById(issueId).orElseThrow(()->new IssueNotFoundException("Issue not found "+ issueId));
    }
    
    public long countIssues() {
    	return issueRepo.count();
    }
    
}
