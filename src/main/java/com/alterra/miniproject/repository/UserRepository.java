package com.alterra.miniproject.repository;

import com.alterra.miniproject.domain.dao.User;
import com.alterra.miniproject.repository.softdeletes.SoftDeletesRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends SoftDeletesRepository<User, Long>{
    User getDistinctTopByUsername(String username);
}
