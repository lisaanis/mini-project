package com.alterra.miniproject.service;

import com.alterra.miniproject.domain.dao.User;
import com.alterra.miniproject.payload.TokenResponse;
import com.alterra.miniproject.payload.UsernamePassword;

public interface AuthService {
    User register(UsernamePassword req);
    TokenResponse generateToken(UsernamePassword req);
}
