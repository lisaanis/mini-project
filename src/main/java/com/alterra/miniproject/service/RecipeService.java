package com.alterra.miniproject.service;

import java.util.Map;
import java.util.Optional;

import com.alterra.miniproject.domain.dao.Recipe;
import com.alterra.miniproject.domain.dao.RecipeDetail;
import com.alterra.miniproject.domain.dto.RecipeRequest;
import com.alterra.miniproject.repository.CategoryRepository;
import com.alterra.miniproject.repository.RecipeDetailRepository;
import com.alterra.miniproject.repository.RecipeRepository;
import com.alterra.miniproject.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
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

        Recipe recipe = Recipe.builder()
            .user(user.get())
            .detail(RecipeDetail.builder()
                    .ingredients(request.getIngredients())
                    .directions(request.getDirections())
                    .build())
            .recipeName(request.getRecipeName())
            .description(request.getDescription()
            .build();
        return ResponseEntity.ok().body(recipeRepository.save(recipe));
    }

    public ResponseEntity<Object> getAllRecipe() {
        return ResponseEntity.ok().body(recipeRepository.findAll());
    }

    public ResponseEntity<Object> getRecipeDetail(Long recipeId) {
        log.info("Find recipe detail by recipe id: {}", recipeId);
        Optional<RecipeDetail> recipeDetail = recipeDetailRepository.findOne(recipeId);
        if (recipeDetail.isEmpty()) return ResponseEntity.badRequest().body(Map.ofEntries(Map.entry("message", "Data not found")));

        return ResponseEntity.ok().body(recipeDetail.get());
    }
}
