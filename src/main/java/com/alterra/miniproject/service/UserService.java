package com.alterra.miniproject.service;

import com.alterra.miniproject.constant.constants;
import com.alterra.miniproject.domain.dao.User;
import com.alterra.miniproject.domain.dto.UserRequest;
import com.alterra.miniproject.repository.UserRepository;
import com.alterra.miniproject.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> save(UserRequest request) {
        log.info("Save new user: {}", request);
        User user = User.builder()
            .fullName(request.getFullname())
            .build();
        try {
            user = userRepository.save(user);
            return ResponseUtil.build(constants.ResponseCode.SUCCESS, user, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(constants.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getAll() {
        log.info("Get all users");
        return ResponseUtil.build(constants.ResponseCode.SUCCESS, userRepository.findAll(), HttpStatus.OK);
    }

}