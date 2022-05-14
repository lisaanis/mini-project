// package com.alterra.miniproject.service;

// import java.util.List;
// import java.util.Objects;

// import com.alterra.miniproject.domain.common.ApiResponse;
// import com.alterra.miniproject.domain.dao.Comment;
// import com.alterra.miniproject.repository.CommentRepository;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.when;

// public class CommentServiceTest {

//     @MockBean
//     private CommentRepository commentRepository;

//     @Autowired
//     private CommentService commentService;

//     @Test
//     void testAddComment() {

//     }

//     @Test
//     void testGetAllComments() {
//         when(commentRepository.findAll())
//             .thenReturn(List.of(Comment.builder()
//                 .id(1L)
//                 //.user(user)
//                 //.recipe("recipe")
//                 .comment("Ini terlihat lezat")
//                 .rating(5)
//                 .build()));

//         ResponseEntity<Object> responseEntity = commentService.getAllComments();
//         ApiResponse response = (ApiResponse) responseEntity.getBody();
//         List<Comment> categories = (List<Comment>) Objects.requireNonNull(response).getData();
        
//         assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//         assertEquals(1, categories.size());
//     }

//     @Test
//     void testGetCommentDetail() {

//     }

//     @Test
//     void testUpdateComment() {

//     }

//     @Test
//     void testDeleteComment() {

//     }
// }
