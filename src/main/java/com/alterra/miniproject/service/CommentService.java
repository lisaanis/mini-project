package com.alterra.miniproject.service;

import java.util.Map;
import java.util.Optional;

import com.alterra.miniproject.domain.dao.Comment;
import com.alterra.miniproject.domain.dao.Recipe;
import com.alterra.miniproject.domain.dao.User;
import com.alterra.miniproject.domain.dto.CommentRequest;
import com.alterra.miniproject.repository.CommentRepository;
import com.alterra.miniproject.repository.RecipeRepository;
import com.alterra.miniproject.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository, RecipeRepository recipeRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    public ResponseEntity<Object> addComment(CommentRequest request) {
        log.info("Save new comment: {}", request);

        log.info("Find user by user id");
        Optional<User> user = userRepository.findOne(request.getUserId());
        if (user.isEmpty()) return ResponseEntity.notFound().build();

        log.info("Find recipe by recipe id");
        Optional<Recipe> recipe = recipeRepository.findOne(request.getRecipeId());
        if (recipe.isEmpty()) return ResponseEntity.notFound().build();

        Comment comment = Comment.builder()
            .user(user.get())
            .recipe(recipe.get())
            .comment(request.getComment())
            .rating(request.getRating())
            .build();

        return ResponseEntity.ok().body(commentRepository.save(comment));
    }

    public ResponseEntity<Object> getAllComments() {
        return ResponseEntity.ok().body(commentRepository.findAll());
    }

    public ResponseEntity<Object> getCommentDetail(Long id) {
        log.info("Find comment detail by comment id: {}", id);
        Optional<Comment> comment = commentRepository.findOne(id);
        if (comment.isEmpty()) return ResponseEntity.badRequest().body(Map.ofEntries(Map.entry("message", "Data not found")));

        return ResponseEntity.ok().body(comment.get());
    }

    public ResponseEntity<Object> updateComment(CommentRequest request, Long id) {
        log.info("Update comment: {}", request);
        Optional<Comment> comment = commentRepository.findOne(id);
        if (comment.isEmpty()) return ResponseEntity.badRequest().body(Map.ofEntries(Map.entry("message", "Data not found")));

        comment.get().setComment(request.getComment());
        comment.get().setRating(request.getRating());
        commentRepository.save(comment.get());
        return ResponseEntity.ok().body(comment.get());
    }

    public ResponseEntity<Object> deleteComment(Long id) {
        log.info("Find comment detail by comment id for delete: {}", id);
        try {
            commentRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Data not found. Error: {}", e.getMessage());
            return ResponseEntity.badRequest().body(Map.ofEntries(Map.entry("message", "Data not found")));
        }
        return ResponseEntity.ok().body(Map.ofEntries(Map.entry("message", "ok")));
    }
}
