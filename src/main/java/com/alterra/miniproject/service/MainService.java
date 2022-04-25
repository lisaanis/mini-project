package com.alterra.miniproject.service;

import com.alterra.miniproject.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    public ResponseEntity<Object> main() {
        return ResponseUtil.build("Hello World, Welcome to my mini project! by: Lisa Anis Safitri", null, HttpStatus.OK);
    }

    public ResponseEntity<Object> error(String message) {
        return ResponseUtil.build(message, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
