package com.alterra.miniproject.service;

import java.util.Map;
import java.util.Optional;

import com.alterra.miniproject.domain.dao.User;
import com.alterra.miniproject.domain.dao.Category;
import com.alterra.miniproject.domain.dao.Recipe;
import com.alterra.miniproject.domain.dao.RecipeDetail;
import com.alterra.miniproject.domain.dto.RecipeRequest;
import com.alterra.miniproject.repository.CategoryRepository;
import com.alterra.miniproject.repository.RecipeDetailRepository;
import com.alterra.miniproject.repository.RecipeRepository;
import com.alterra.miniproject.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        log.info("Save new recipe: {}", request);

        log.info("Find user by user id");
        Optional<User> user = userRepository.findOne(request.getUserId());
        if(user.isEmpty()) return ResponseEntity.notFound().build();

        log.info("Find category by category id");
        Optional<Category> category = categoryRepository.findOne(request.getCategoryId());
        if(category.isEmpty()) return ResponseEntity.notFound().build();

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

        return ResponseEntity.ok().body(recipeRepository.save(recipe));
    }

    public ResponseEntity<Object> getAllRecipe() {
        return ResponseEntity.ok().body(recipeRepository.findAll());
    }

    public ResponseEntity<Object> getRecipeDetail(Long recipeId) {
        log.info("Find recipe detail by recipe id: {}", recipeId);
        Optional<RecipeDetail> recipeDetail = recipeDetailRepository.findOne(recipeId);
        if (recipeDetail.isEmpty()) return ResponseEntity.badRequest().body(Map.ofEntries(Map.entry("message", "Data tidak ditemukan")));

        return ResponseEntity.ok().body(recipeDetail.get());
    }

    public ResponseEntity<Object> deleteRecipe(Long recipeId) {
        log.info("Find book detail by recipe id for delete: {}", recipeId);
        try{
            recipeDetailRepository.delete(recipeId);
            recipeRepository.delete(recipeId);
        } catch (EmptyResultDataAccessException e) {
            log.error("Data not found, Error: {}", e.getMessage());
            return ResponseEntity.badRequest().body(Map.ofEntries(Map.entry("mesage", "Data tidak ditemukan")));
        }
        return ResponseEntity.ok().body(Map.ofEntries(Map.entry("message", "berhasil hapus")));
    }

    public ResponseEntity<Object> updateRecipe(RecipeRequest request, Long recipeId) {
        log.info("Update recipe: {}", request);
        Optional<Recipe> recipe = recipeRepository.findOne(recipeId);
        if (recipe.isEmpty()) return ResponseEntity.badRequest().body(Map.ofEntries(Map.entry("message", "Data tidak ditemukan")));

        log.info("Find category by category id");
        Optional<Category> category = categoryRepository.findOne(request.getCategoryId());
        if(category.isEmpty()) return ResponseEntity.notFound().build();

        recipe.get().setRecipeName(request.getRecipeName());
        recipe.get().setCategory(category.get());
        recipe.get().setDescription(request.getDescription());
        recipe.get().setDetail(RecipeDetail.builder()
            .ingredients(request.getIngredients())
            .directions(request.getDirections())
            .build());
        
        recipeRepository.save(recipe.get());
        return ResponseEntity.ok().body(recipe.get());
    }
}
