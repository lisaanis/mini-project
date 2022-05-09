package com.alterra.miniproject.repository;

import com.alterra.miniproject.domain.dao.Category;
import com.alterra.miniproject.repository.softdeletes.SoftDeletesRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends SoftDeletesRepository<Category, Long> {
    
}
