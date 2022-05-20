package com.alterra.miniproject.service;

import java.util.Optional;

import com.alterra.miniproject.domain.dao.Comment;
import com.alterra.miniproject.domain.dao.Recipe;
import com.alterra.miniproject.domain.dao.User;
import com.alterra.miniproject.domain.dto.CommentRequest;
import com.alterra.miniproject.constant.constants;
import com.alterra.miniproject.repository.CommentRepository;
import com.alterra.miniproject.repository.RecipeRepository;
import com.alterra.miniproject.repository.UserRepository;
import com.alterra.miniproject.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
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
        try { 
            log.info("Save new comment: {}", request);

            log.info("Find user by user id");
            Optional<User> user = userRepository.findOne(request.getUserId());
            if (user.isEmpty()) return ResponseUtil.build(constants.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);

            log.info("Find recipe by recipe id");
            Optional<Recipe> recipe = recipeRepository.findOne(request.getRecipeId());
            if (recipe.isEmpty()) return ResponseUtil.build(constants.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);

            Comment comment = Comment.builder()
                .user(user.get())
                .recipe(recipe.get())
                .comment(request.getComment())
                .rating(request.getRating())
                .build();
                
            comment = commentRepository.save(comment);
            return ResponseUtil.build(constants.ResponseCode.SUCCESS, comment, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by executing create new comment, Error : {}",e.getMessage());
            return ResponseUtil.build(constants.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getAllComments() {
        try {
            log.info("Get all comments");
            return ResponseUtil.build(constants.ResponseCode.SUCCESS, commentRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by get all comments, Error : {}",e.getMessage());
            return ResponseUtil.build(constants.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getCommentDetail(Long id) {
        try {
            log.info("Find comment detail by comment id: {}", id);
            Optional<Comment> comment = commentRepository.findOne(id);
            if (comment.isEmpty()) {
                log.info("comment not found");
                return ResponseUtil.build(constants.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }
            return ResponseUtil.build(constants.ResponseCode.SUCCESS, comment.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by executing get comment by id, Error : {}",e.getMessage());
            return ResponseUtil.build(constants.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateComment(CommentRequest request, Long id) {
        try {
            log.info("Update comment: {}", request);
            Optional<Comment> comment = commentRepository.findOne(id);
            if (comment.isEmpty()) {
                log.info("comment not found");
                return ResponseUtil.build(constants.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }

            comment.get().setComment(request.getComment());
            comment.get().setRating(request.getRating());
            commentRepository.save(comment.get());
            return ResponseUtil.build(constants.ResponseCode.SUCCESS, comment.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by update comment, Error : {}",e.getMessage());
            return ResponseUtil.build(constants.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteComment(Long id) {
        try {
            log.info("Executing delete comment by id: {}", id);
            commentRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Data not found. Error: {}", e.getMessage());
            return ResponseUtil.build(constants.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
        }
        return ResponseUtil.build(constants.ResponseCode.SUCCESS, null, HttpStatus.OK);
    }
}
