package com.alterra.miniproject.service;

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
        try {
            log.info("Get all categories");
            return ResponseUtil.build(constants.ResponseCode.SUCCESS, categoryRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by get all categories, Error : {}",e.getMessage());
            return ResponseUtil.build(constants.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getCategoryDetail (Long id) {
        try {
            log.info("Find category detail by category id: {}", id);
            Optional<Category> categoryDetail = categoryRepository.findOne(id);
            if (categoryDetail.isEmpty()) {
                log.info("category not found");
                return ResponseUtil.build(constants.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }
            return ResponseUtil.build(constants.ResponseCode.SUCCESS, categoryDetail.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by executing get category by id, Error : {}",e.getMessage());
            return ResponseUtil.build(constants.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateCategory(CategoryRequest request, Long id) {
        try {
            log.info("Update category: {}", request);
            Optional<Category> category = categoryRepository.findOne(id);
            if (category.isEmpty()) {
                log.info("category not found");
                return ResponseUtil.build(constants.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }
            category.get().setCategory(request.getCategory());
            categoryRepository.save(category.get());
            return ResponseUtil.build(constants.ResponseCode.SUCCESS, category.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by update category, Error : {}",e.getMessage());
            return ResponseUtil.build(constants.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteCategory(Long id) {
        try {
            log.info("Executing delete category by id: {}", id);
            categoryRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Data not found. Error: {}", e.getMessage());
            return ResponseUtil.build(constants.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
        }
        return ResponseUtil.build(constants.ResponseCode.SUCCESS, null, HttpStatus.OK);
    }

}
