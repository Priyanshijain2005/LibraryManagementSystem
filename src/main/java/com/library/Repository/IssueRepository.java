package com.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long> {}
