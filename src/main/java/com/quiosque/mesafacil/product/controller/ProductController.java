package com.quiosque.mesafacil.product.controller;

import com.quiosque.mesafacil.product.dto.CreateProductDTO;
import com.quiosque.mesafacil.product.dto.ResponseProductDTO;
import com.quiosque.mesafacil.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseProductDTO> createProduct(@RequestBody CreateProductDTO dto, @RequestHeader("Authorization") String token) {
        return productService.createProduct(dto, token);
    }

    @GetMapping
    public List<ResponseProductDTO> getAllProducts(@RequestHeader("Authorization") String token) {
        return productService.getAllProducts(token);
    }
}
