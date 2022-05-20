package com.alterra.miniproject.repository;

import com.alterra.miniproject.domain.dao.Recipe;
import com.alterra.miniproject.repository.softdeletes.SoftDeletesRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends SoftDeletesRepository<Recipe, Long> {
    
}
