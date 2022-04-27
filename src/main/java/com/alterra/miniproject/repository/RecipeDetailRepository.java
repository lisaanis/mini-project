package com.alterra.miniproject.repository;

import com.alterra.miniproject.domain.dao.RecipeDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeDetailRepository extends JpaRepository<RecipeDetail, Long> {
    
}
