package com.quiosque.mesafacil.Product.Controller;

import com.quiosque.mesafacil.Product.DTOs.CreateProductDTO;
import com.quiosque.mesafacil.Product.DTOs.ResponseProductDTO;
import com.quiosque.mesafacil.Product.Entity.ProductEntity;
import com.quiosque.mesafacil.Product.Service.ProductService;
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
