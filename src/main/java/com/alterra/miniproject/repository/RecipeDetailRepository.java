package com.alterra.miniproject.repository;

import com.alterra.miniproject.domain.dao.RecipeDetail;
import com.alterra.miniproject.repository.softdeletes.SoftDeletesRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RecipeDetailRepository extends SoftDeletesRepository<RecipeDetail, Long> {
    
}
