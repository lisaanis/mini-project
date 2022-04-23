package com.alterra.miniproject.controller;

import com.alterra.miniproject.service.MainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    
    @Autowired
    private MainService mainService;

    @GetMapping("/")
    public ResponseEntity<Object> hello() {
        return mainService.main();
    }
}
