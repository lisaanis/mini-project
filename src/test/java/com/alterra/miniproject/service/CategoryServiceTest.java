// package com.alterra.miniproject.service;

// import static org.mockito.ArgumentMatchers.any;

// import com.alterra.miniproject.constant.constants;
// import com.alterra.miniproject.domain.common.ApiResponse;
// import com.alterra.miniproject.domain.dao.Category;
// import com.alterra.miniproject.domain.dto.CategoryRequest;
// import com.alterra.miniproject.repository.CategoryRepository;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.test.context.junit.jupiter.SpringExtension;

// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Objects;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.when;

// @ExtendWith(SpringExtension.class)
// @SpringBootTest(classes = CategoryService.class)
// public class CategoryServiceTest {

//     @MockBean
//     private CategoryRepository categoryRepository;

//     @Autowired
//     private CategoryService categoryService;

//     @Test
//     void testAddCategory() {
//         // when(categoryRepository.save(any()))
//         //     .thenReturn(Category.builder())
//         //         .id(1L)
//         //         .createdAt(LocalDateTime.now())
//         //         .createdBy(constants.DEFAULT_SYSTEM)
//         //         .category("Masakan Jawa")
//         //         .build();
        
//         // ResponseEntity<Object> responseEntity = categoryService.addCategory(CategoryRequest.builder().category("Masakan Jawa").build());
//         // ApiResponse response = (ApiResponse) responseEntity.getBody();
//         // Category category = (Category) Objects.requireNonNull(response).getData();

//         // assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//         // assertEquals("Success", response.getMessage());
//         // assertEquals(1L, category.getId());
//         // assertEquals("Masakan Jawa", category.getCategory());
//     }

//     @Test
//     void testGetAllCategories() {
//         when(categoryRepository.findAll())
//             .thenReturn(List.of(Category.builder()
//                 .id(1L)
//                 // .createdAt(LocalDateTime.now())
//                 // .createdBy(constants.DEFAULT_SYSTEM)
//                 .category("Masakan Jawa")
//                 .build()));

//         ResponseEntity<Object> responseEntity = categoryService.getAllCategories();
//         ApiResponse response = (ApiResponse) responseEntity.getBody();
//         List<Category> categories = (List<Category>) Objects.requireNonNull(response).getData();
        
//         assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//         assertEquals(1, categories.size());
//     }

//     @Test
//     void testGetCategoryDetail() {

//     }

//     @Test
//     void testDeleteCategory() {

//     }

//     @Test
//     void testUpdateCategory() {

//     }
// }
