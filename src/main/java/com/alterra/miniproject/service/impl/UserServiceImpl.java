package com.alterra.miniproject.service.impl;

import com.alterra.miniproject.domain.dao.User;
import com.alterra.miniproject.repository.UserRepository;
import com.alterra.miniproject.service.UserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getDistinctTopByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Username Not Found");
        
        return user;
    }
}
