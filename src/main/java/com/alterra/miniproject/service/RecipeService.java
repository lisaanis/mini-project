package com.alterra.miniproject.service;

import java.util.Optional;

import com.alterra.miniproject.domain.dao.User;
import com.alterra.miniproject.constant.constants;
import com.alterra.miniproject.domain.dao.Category;
import com.alterra.miniproject.domain.dao.Recipe;
import com.alterra.miniproject.domain.dao.RecipeDetail;
import com.alterra.miniproject.domain.dto.RecipeRequest;
import com.alterra.miniproject.repository.CategoryRepository;
import com.alterra.miniproject.repository.RecipeDetailRepository;
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
public class RecipeService {
    
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeDetailRepository recipeDetailRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public RecipeService(UserRepository userRepository, RecipeRepository recipeRepository, RecipeDetailRepository recipeDetailRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.recipeDetailRepository = recipeDetailRepository;
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<Object> addRecipe(RecipeRequest request) {
        try {
            log.info("Save new recipe: {}", request);

            log.info("Find user by user id");
            Optional<User> user = userRepository.findOne(request.getUserId());
            if(user.isEmpty()) return ResponseUtil.build(constants.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);

            log.info("Find category by category id");
            Optional<Category> category = categoryRepository.findOne(request.getCategoryId());
            if(category.isEmpty()) return ResponseUtil.build(constants.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);

            Recipe recipe = Recipe.builder()
                .user(user.get())
                .recipeName(request.getRecipeName())
                .category(category.get())
                .description(request.getDescription())
                .detail(RecipeDetail.builder()
                        .ingredients(request.getIngredients())
                        .directions(request.getDirections())
                        .build())
                .build();

            recipe = recipeRepository.save(recipe);
            return ResponseUtil.build(constants.ResponseCode.SUCCESS, recipe, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by executing create new recipe, Error : {}",e.getMessage());
            return ResponseUtil.build(constants.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getAllRecipe() {
        try {
            log.info("Get all recipe");
            return ResponseUtil.build(constants.ResponseCode.SUCCESS, recipeRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by get all recipes, Error : {}",e.getMessage());
            return ResponseUtil.build(constants.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getRecipeDetail(Long recipeId) {
        try {
            log.info("Find recipe detail by recipe id: {}", recipeId);
            Optional<RecipeDetail> recipeDetail = recipeDetailRepository.findOne(recipeId);
            if (recipeDetail.isEmpty()) {
                log.info("recipe not found");
                return ResponseUtil.build(constants.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }
            return ResponseUtil.build(constants.ResponseCode.SUCCESS, recipeDetail.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by executing get recipe by id, Error : {}",e.getMessage());
            return ResponseUtil.build(constants.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateRecipe(RecipeRequest request, Long recipeId) {
        try {
            log.info("Update recipe: {}", request);
            
            log.info("Find recipe by category id");
            Optional<Recipe> recipe = recipeRepository.findOne(recipeId);
            if (recipe.isEmpty()) {
                log.info("recipe not found");
                return ResponseUtil.build(constants.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }
            log.info("Find category by category id");
            Optional<Category> category = categoryRepository.findOne(request.getCategoryId());
            if(category.isEmpty()) {
                log.info("category not found");
                return ResponseUtil.build(constants.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }
            log.info("Find recipe detail by recipe id: {}", recipeId);
            Optional<RecipeDetail> recipeDetail = recipeDetailRepository.findOne(recipeId);
            if (recipeDetail.isEmpty()) {
                log.info("recipe detail not found");
                return ResponseUtil.build(constants.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }
            
            recipeDetail.get().setIngredients(request.getIngredients());
            recipeDetail.get().setDirections(request.getDirections());

            recipe.get().setRecipeName(request.getRecipeName());
            recipe.get().setCategory(category.get());
            recipe.get().setDescription(request.getDescription());
            
            recipeRepository.save(recipe.get());
            return ResponseUtil.build(constants.ResponseCode.SUCCESS, recipe.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by update recipe, Error : {}",e.getMessage());
            return ResponseUtil.build(constants.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteRecipe(Long recipeId) {
        try{
            log.info("Executing delete recipe by id: {}", recipeId);
            recipeDetailRepository.delete(recipeId);
            recipeRepository.delete(recipeId);
        } catch (EmptyResultDataAccessException e) {
            log.error("Data not found, Error: {}", e.getMessage());
            return ResponseUtil.build(constants.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
        }
        return ResponseUtil.build(constants.ResponseCode.SUCCESS, null, HttpStatus.OK);
    }
}
