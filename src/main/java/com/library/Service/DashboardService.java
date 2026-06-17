package com.library.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.Repository.BookRepository;
import com.library.Repository.IssueRepository;
import com.library.Repository.UserRepository;
import com.library.dto.DashboardResponse;

@Service
public class DashboardService {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private IssueRepository issueRepo;

    public DashboardResponse getDashboardStats(){

        long totalBooks = bookRepo.count();

        long totalUsers = userRepo.count();

        long totalIssuedBooks = issueRepo.count();

        return new DashboardResponse(
                totalBooks,
                totalUsers,
                totalIssuedBooks
        );
    }
}