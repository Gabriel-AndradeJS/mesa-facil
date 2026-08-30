package com.quiosque.mesafacil.product.service;

import com.quiosque.mesafacil.configs.JwtService;
import com.quiosque.mesafacil.product.dto.CreateProductDTO;
import com.quiosque.mesafacil.product.dto.ResponseProductDTO;
import com.quiosque.mesafacil.product.entity.ProductEntity;
import com.quiosque.mesafacil.product.repository.ProductRepository;
import com.quiosque.mesafacil.product.mapper.ProductMapper;
import com.quiosque.mesafacil.user.dto.WaiterDTO;
import com.quiosque.mesafacil.user.entity.UserEntity;
import com.quiosque.mesafacil.user.service.UserService;
import com.quiosque.mesafacil.user.service.WaiterService;
import com.quiosque.mesafacil.user.enums.UserRole;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {

    private final JwtService jwtService;
    private final WaiterService waiterService;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Transactional
    public ResponseEntity<ResponseProductDTO> createProduct(
            CreateProductDTO createProductDTO,
            String token) {

        String tokenString = token.replace("Bearer ", "");

        Long userId = jwtService.extractClaimId(tokenString, "id").longValue();

        UserEntity user = userService.getUserById(userId);

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        ProductEntity product = new ProductEntity();

        product.setName(createProductDTO.getName());
        product.setPrice(createProductDTO.getPrice());
        product.setDescription(createProductDTO.getDescription());
        product.setStatus(createProductDTO.getStatus());
        product.setQuantity(createProductDTO.getQuantity());

        product.setCreatedBy(user);


        if (user.getRole() == UserRole.ADMIN) {

            product.setAdmin(user);

        } else if (user.getRole() == UserRole.WAITER) {

            WaiterDTO waiterDTO =
                    waiterService.getWaiterUserById(userId);

            if (waiterDTO == null) {
                return ResponseEntity.badRequest().build();
            }

            UserEntity admin =
                    userService.getUserById(waiterDTO.getAdminId());

            if (admin == null || admin.getRole() != UserRole.ADMIN) {
                return ResponseEntity.badRequest().build();
            }

            product.setAdmin(admin);

        } else {
            return ResponseEntity.badRequest().build();
        }

        ProductEntity savedProduct =
                productRepository.save(product);

        ResponseProductDTO response = new ResponseProductDTO();

        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setPrice(savedProduct.getPrice());
        response.setDescription(savedProduct.getDescription());
        response.setStatus(savedProduct.getStatus());
        response.setQuantity(savedProduct.getQuantity());
        response.setWaiterName(user.getName());

        response.setCreatedById(savedProduct.getCreatedBy().getId());

        return ResponseEntity.ok(response);
    }

    public List<ResponseProductDTO> getAllProducts(Long userId){
        UserEntity user = userService.getUserById(userId);


        if (user.getRole() == UserRole.ADMIN) {
            List<ProductEntity> products = productRepository.findAllProduct(user.getId());
            return products.stream().map(mapper::productToResponse).toList();
        } else if (user.getRole() == UserRole.WAITER) {
            WaiterDTO waiterDTO =
                    waiterService.getWaiterUserById(user.getId());
            List<ProductEntity> products = productRepository.findAllProduct(waiterDTO.getAdminId());
            return products.stream().map(mapper::productToResponse).toList();

        }
        return new ArrayList<>();
    }
     }