package com.alterra.miniproject.controller;

import com.alterra.miniproject.domain.dto.RecipeRequest;
import com.alterra.miniproject.service.RecipeService;

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
@RequestMapping(value = "/recipe")
public class RecipeController {
    
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> addRecipe(@RequestBody RecipeRequest request) {
        return recipeService.addRecipe(request);
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getAllRecipe() {
        return recipeService.getAllRecipe();
    }

    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<Object> getRecipeDetail(@PathVariable(value = "id") Long recipeId) {
        return recipeService.getRecipeDetail(recipeId);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteRecipe(@PathVariable(value = "id") Long recipeId) {
        return recipeService.deleteRecipe(recipeId);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateRecipe(@PathVariable(value = "id") Long recipeId, @RequestBody RecipeRequest request) {
        return recipeService.updateRecipe(request, recipeId);
    }
}