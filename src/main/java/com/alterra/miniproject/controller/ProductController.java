package com.alterra.miniproject.controller;

import com.alterra.miniproject.domain.dto.ProductRequest;
import com.alterra.miniproject.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @PostMapping(value = "")
    public ResponseEntity<Object> addProduct(@RequestBody ProductRequest request) {
        return productService.addProduct(request);
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getAll() {
        return productService.getAll();
    }

    // @GetMapping(value = "/{id}")
    // public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id) {
    //     return productService.getById(id);
    // }
}
