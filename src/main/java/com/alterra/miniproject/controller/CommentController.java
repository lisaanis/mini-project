package com.alterra.miniproject.controller;

import com.alterra.miniproject.domain.dto.CommentRequest;
import com.alterra.miniproject.service.CommentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {
    
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> addComment(@RequestBody CommentRequest request) {
        return commentService.addComment(request);
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getCommentDetail(@PathVariable(value = "id") Long id) {
        return commentService.getCommentDetail(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable(value = "id") Long id) {
        return commentService.deleteComment(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateComment(@PathVariable(value = "id") Long id, @RequestBody CommentRequest request) {
        return commentService.updateComment(request, id);
    }
}
