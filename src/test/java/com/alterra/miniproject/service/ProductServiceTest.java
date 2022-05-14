// package com.alterra.miniproject.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.when;
// import static org.mockito.ArgumentMatchers.any;

// import java.util.List;
// import java.util.Objects;

// import com.alterra.miniproject.domain.common.ApiResponse;
// import com.alterra.miniproject.domain.dao.Product;
// import com.alterra.miniproject.domain.dto.ProductRequest;
// import com.alterra.miniproject.repository.ProductRepository;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.test.context.junit.jupiter.SpringExtension;

// @ExtendWith(SpringExtension.class)
// @SpringBootTest(classes = ProductService.class)
// public class ProductServiceTest {
//     @MockBean
//     private ProductRepository productRepository;

//     @Autowired
//     private ProductService productService;

//     @Test
//     void testAddProduct() {
//         when(productRepository.save(any()))
//             .thenReturn(Product.builder()
//                 .id(1L)
//                 .productName("Minyak Goreng")
//                 .price(22000)
//                 .material("Sukses")
//                 .build());
        
//                 ResponseEntity<Object> responseEntity = productService.addProduct(ProductRequest.builder().material("Sukses").build());
//                 ApiResponse response = (ApiResponse) responseEntity.getBody();
//                 Product product = (Product) Objects.requireNonNull(response).getData();
                
//                 assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//                 assertEquals("Success", response.getMessage());
//                 assertEquals(1L, product.getId());
//                 assertEquals("Sukses", product.getMaterial());
//     }

//     @Test
//     void testGetAll() {
//         when(productRepository.findAll())
//             .thenReturn(List.of(Product.builder()
//                 .id(1L)
//                 .productName("Minyak Goreng")
//                 .price(22000)
//                 .material("Sukses")
//                 .build()));
        
//         ResponseEntity<Object> responseEntity = productService.getAll();
//         ApiResponse response = (ApiResponse) responseEntity.getBody();
    
//         List<Product> products = (List<Product>) Objects.requireNonNull(response).getData();
//         assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//         assertEquals(1, products.size());

//     }

//     // @Test
//     // void testGetById() {

//     // }
// }
