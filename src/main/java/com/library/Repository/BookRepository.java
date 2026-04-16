package com.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}
