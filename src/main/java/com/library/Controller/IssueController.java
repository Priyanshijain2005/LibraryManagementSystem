package com.library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.library.Service.IssueService;

@RestController
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueService service;

    // Issue Book
    @PostMapping("/issue/{bookId}/{userId}")
    public String issueBook(@PathVariable Long bookId,
                            @PathVariable Long userId) {
        return service.issueBook(bookId, userId);
    }

    // Return Book (BETTER: PUT instead of POST)
    @PutMapping("/return/{issueId}")
    public String returnBook(@PathVariable Long issueId) {
        return service.returnBook(issueId);
    }
}