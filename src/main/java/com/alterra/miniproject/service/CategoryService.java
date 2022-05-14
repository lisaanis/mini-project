package com.alterra.miniproject.service;

import java.util.Map;
import java.util.Optional;

import com.alterra.miniproject.constant.constants;
import com.alterra.miniproject.domain.dao.Category;
import com.alterra.miniproject.domain.dto.CategoryRequest;
import com.alterra.miniproject.repository.CategoryRepository;
import com.alterra.miniproject.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<Object> addCategory(CategoryRequest request) {
        log.info("Save new category: {}", request);
        Category category = Category.builder()
            .category(request.getCategory())
            .build();
        
        try {
            category = categoryRepository.save(category);
            return ResponseUtil.build(constants.ResponseCode.SUCCESS, category, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(constants.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getAllCategories() {
        log.info("Get all categories");
        return ResponseEntity.ok().body(categoryRepository.findAll());
    }

    public ResponseEntity<Object> getCategoryDetail (Long id) {
        log.info("Find category detail by category id: {}", id);
        Optional<Category> categoryDetail = categoryRepository.findOne(id);
        if (categoryDetail.isEmpty()) return ResponseEntity.badRequest().body(Map.ofEntries(Map.entry("message", "Data not found")));

        return ResponseEntity.ok().body(categoryDetail.get());
    }

    public ResponseEntity<Object> updateCategory(CategoryRequest request, Long id) {
        log.info("Update category: {}", request);
        Optional<Category> category = categoryRepository.findOne(id);
        if (category.isEmpty()) return ResponseEntity.badRequest().body(Map.ofEntries(Map.entry("message", "Data not found")));

        category.get().setCategory(request.getCategory());
        categoryRepository.save(category.get());
        return ResponseEntity.ok().body(category.get());
    }

    public ResponseEntity<Object> deleteCategory(Long id) {
        log.info("Find category detail by category id for delete: {}", id);
        try {
            categoryRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Data not found. Error: {}", e.getMessage());
            return ResponseEntity.badRequest().body(Map.ofEntries(Map.entry("message", "Data not found")));
        }
        return ResponseEntity.ok().body(Map.ofEntries(Map.entry("message", "ok")));
    }

}
