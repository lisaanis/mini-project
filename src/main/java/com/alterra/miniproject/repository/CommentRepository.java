package com.alterra.miniproject.repository;

import com.alterra.miniproject.domain.dao.Comment;
import com.alterra.miniproject.repository.softdeletes.SoftDeletesRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends SoftDeletesRepository<Comment, Long> {
    
}
