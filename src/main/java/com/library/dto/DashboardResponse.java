package com.library.dto;

public class DashboardResponse {

    private long totalBooks;
    private long totalUsers;
    private long totalIssuedBooks;

    public DashboardResponse(long totalBooks,
                             long totalUsers,
                             long totalIssuedBooks) {
        this.totalBooks = totalBooks;
        this.totalUsers = totalUsers;
        this.totalIssuedBooks = totalIssuedBooks;
    }

    public long getTotalBooks() {
        return totalBooks;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public long getTotalIssuedBooks() {
        return totalIssuedBooks;
    }
}