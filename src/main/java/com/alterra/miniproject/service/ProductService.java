package com.alterra.miniproject.service;

import java.util.Optional;

import com.alterra.miniproject.domain.dao.Product;
import com.alterra.miniproject.domain.dto.ProductRequest;
import com.alterra.miniproject.repository.ProductRepository;
import com.alterra.miniproject.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {
    
    @Autowired 
    private ProductRepository productRepository;

    public ResponseEntity<Object> addProduct(ProductRequest request) {
        log.info("Add product");
        Product product = Product.builder()
            .material(request.getMaterial())
            .material(request.getMaterial())
            .build();
        product = productRepository.save(product);
        return ResponseUtil.build("Success", product, HttpStatus.OK);
    }

    public ResponseEntity<Object> getAll() {
        log.info("Get all products");
        return ResponseUtil.build("Success", productRepository.findAll(), HttpStatus.OK);
    }

    // public ResponseEntity<Object> getById(Long id) {
    //     log.info("Get product by id: {}", id);
    //     Optional<Product> product = productRepository.findById(id);
    //     if (product.isEmpty())
    //         return ResponseUtil.build("Success", product, HttpStatus.OK);
        
    //     return ResponseEntity.ok().body(product.get());
    // }
    
}
