// package com.alterra.miniproject.service;

// import java.util.List;
// import java.util.Objects;
// import java.util.Optional;

// import com.alterra.miniproject.domain.common.ApiResponse;
// import com.alterra.miniproject.domain.dao.Category;
// import com.alterra.miniproject.domain.dao.Comment;
// import com.alterra.miniproject.domain.dao.Recipe;
// import com.alterra.miniproject.domain.dao.RecipeDetail;
// import com.alterra.miniproject.domain.dao.User;
// import com.alterra.miniproject.domain.dto.CategoryRequest;
// import com.alterra.miniproject.domain.dto.CommentRequest;
// import com.alterra.miniproject.domain.dto.UserRequest;
// import com.alterra.miniproject.repository.CommentRepository;
// import com.alterra.miniproject.repository.RecipeRepository;
// import com.alterra.miniproject.repository.UserRepository;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyLong;
// import static org.mockito.Mockito.when;

// public class CommentServiceTest {

//     @MockBean
//     private CommentRepository commentRepository;

//     @MockBean
//     private UserRepository userRepository;

//     @MockBean
//     private RecipeRepository recipeRepository;

//     @Autowired
//     private CommentService commentService;
    

//     @Test
//     void testAddComment() {
//         when(userRepository.findOne(anyLong())).thenReturn(Optional.of(User.builder()
//             .id(1L)
//             .username("lisaanis")
//             .password("1234")
//             .active(true)
//             .build()));

//         when(recipeRepository.findOne(anyLong())).thenReturn(Optional.of(Recipe.builder()
//             .id(1L)
//             .user(User.builder().id(1L).build())
//             .recipeName("Masakan Jawa")
//             .category(Category.builder().id(1L).build())
//             .description("Masakan Jawa Enak")
//             .detail(RecipeDetail.builder()
//                 .ingredients("Bawang, Cabai, Nasi, Telur, Ayam")
//                 .directions("1. Siapkan bumbu dan tumis 2. Masukkan telur, nasi, dan ayam 3. Masak hingga matang")
//                 .build())
//             .build()));

//         when(commentRepository.save(any())).thenReturn(Comment.builder()
//             .id(1L)
//             .user(User.builder().id(1L).build())
//             .recipe(Recipe.builder().id(1L).build())
//             .comment("Ini terlihat lezat")
//             .rating(5)
//             .build());

//         ResponseEntity<Object> responseEntity = commentService.addComment(CommentRequest.builder().comment("Ini terlihat lezat").build());
//         ApiResponse response = (ApiResponse) responseEntity.getBody();
//         Comment comment = (Comment) Objects.requireNonNull(response).getData();

//         assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//         assertEquals("SUCCESS", response.getMessage());
//         assertEquals(1L, comment.getId());
//         assertEquals(1L, comment.getUser().getId());
//         assertEquals(1L, comment.getRecipe().getId());
//         assertEquals("Ini terlihat lezat", comment.getComment());
//         assertEquals(5, comment.getRating());
//     }

//     @Test
//     void testGetAllComments() {
//         when(commentRepository.findAll()).thenReturn(List.of(Comment.builder()
//             .id(1L)
//             .user(User.builder().id(1L).build())
//             .recipe(Recipe.builder().id(1L).build())
//             .comment("Ini terlihat lezat")
//             .rating(5)
//             .build()));

//         ResponseEntity<Object> responseEntity = commentService.getAllComments();
//         ApiResponse response = (ApiResponse) responseEntity.getBody();
//         List<Comment> comments = (List<Comment>) Objects.requireNonNull(response).getData();
        
//         assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//         assertEquals(1, comments.size());
//     }

//     @Test
//     void testGetCommentDetail() {
//         when(commentRepository.findOne(anyLong())).thenReturn(Optional.of(Comment.builder()
//             .id(1L)
//             .user(User.builder().id(1L).build())
//             .recipe(Recipe.builder()
//             .id(1L).build())
//             .comment("Ini terlihat lezat")
//             .rating(5)
//             .build()));

//         ResponseEntity<Object> responseEntity = commentService.getCommentDetail(1L);
//         ApiResponse response = (ApiResponse) responseEntity.getBody();
//         Comment comment = (Comment) Objects.requireNonNull(response).getData();

//         assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//         assertEquals("SUCCESS", response.getMessage());
//         assertEquals(1L, comment.getId());
//         assertEquals(1L, comment.getUser().getId());
//         assertEquals(1L, comment.getRecipe().getId());
//         assertEquals("Ini terlihat lezat", comment.getComment());
//         assertEquals(5, comment.getRating());
//     }

//     @Test
//     void testUpdateComment() {

//     }

//     @Test
//     void testDeleteComment() {

//     }
// }
