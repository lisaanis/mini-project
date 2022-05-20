package com.alterra.miniproject.service;

import com.alterra.miniproject.domain.common.ApiResponse;
import com.alterra.miniproject.domain.dao.Category;
import com.alterra.miniproject.domain.dto.CategoryRequest;
import com.alterra.miniproject.repository.CategoryRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CategoryService.class)
public class CategoryServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    void testAddCategory() {
        
        when(categoryRepository.save(any()))
            .thenReturn(Category.builder()
                .id(1L)
                .category("Masakan Jawa")
                .build());

        ResponseEntity<Object> responseEntity = categoryService.addCategory(CategoryRequest.builder().category("Masakan Jawa").build());
        ApiResponse response = (ApiResponse) responseEntity.getBody();
        Category category = (Category) Objects.requireNonNull(response).getData();

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals("SUCCESS", response.getMessage());
        assertEquals(1L, category.getId());
        assertEquals("Masakan Jawa", category.getCategory());
    }

    @Test
    void testGetAllCategories() {
        when(categoryRepository.findAll())
            .thenReturn(List.of(Category.builder()
                .id(1L)
                .category("Masakan Jawa")
                .build()));
        
        ResponseEntity<Object> responseEntity = categoryService.getAllCategories();
        ApiResponse response = (ApiResponse) responseEntity.getBody();
        List<Category> categories = (List<Category>) Objects.requireNonNull(response).getData();
        
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals("SUCCESS", response.getMessage());
        assertEquals(1, categories.size());
    }

    @Test
    void testGetCategoryDetail() {
        when(categoryRepository.findOne(anyLong()))
            .thenReturn(Optional.of(Category.builder()
                .id(1L)
                .category("Masakan Jawa")
                .build()));

        ResponseEntity<Object> responseEntity = categoryService.getCategoryDetail(1L);
        ApiResponse response = (ApiResponse) responseEntity.getBody();
        Category category = (Category) Objects.requireNonNull(response).getData();

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals("SUCCESS", response.getMessage());
        assertEquals(1L, category.getId());
        assertEquals("Masakan Jawa", category.getCategory());
    }

    @Test
    void testDeleteCategory() {
        when(categoryRepository.findOne(anyLong()))
            .thenReturn(Optional.of(Category.builder()
                .id(1L)
                .category("Masakan Jawa")
                .build()));
        doNothing().when(categoryRepository).delete(anyLong());

        ApiResponse response = (ApiResponse) categoryService.deleteCategory(1L).getBody();
        assertEquals("SUCCESS", response.getMessage());
        verify(categoryRepository, times(1)).delete(anyLong());
    }

    @Test
    void testUpdateCategory() {
        Category category = Category.builder()
            .id(1L)
            .category("Masakan Jawa")
            .build();

        when(categoryRepository.findOne(anyLong())).thenReturn(Optional.of(category));
        when(categoryRepository.save(any())).thenReturn(category);
        ResponseEntity<Object> responseEntity = categoryService.updateCategory(CategoryRequest.builder().category("Masakan Jawa").build(), 1L);
        ApiResponse response = (ApiResponse) responseEntity.getBody();
        Category data = (Category) Objects.requireNonNull(response).getData();

        assertEquals(1L, data.getId());
        assertEquals("Masakan Jawa", data.getCategory());
    }
}
