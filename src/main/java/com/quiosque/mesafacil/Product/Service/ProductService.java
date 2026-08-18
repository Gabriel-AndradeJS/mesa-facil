package com.quiosque.mesafacil.Product.Service;

import com.quiosque.mesafacil.Config.JwtService;
import com.quiosque.mesafacil.Product.DTOs.CreateProductDTO;
import com.quiosque.mesafacil.Product.DTOs.ResponseProductDTO;
import com.quiosque.mesafacil.Product.Entity.ProductEntity;
import com.quiosque.mesafacil.Product.Repository.ProductRepository;
import com.quiosque.mesafacil.User.DTOs.WaiterDTO;
import com.quiosque.mesafacil.User.Entity.UserEntity;
import com.quiosque.mesafacil.User.Entity.WaiterEntity;
import com.quiosque.mesafacil.User.Service.UserService;
import com.quiosque.mesafacil.User.Service.WaiterService;
import com.quiosque.mesafacil.User.enums.UserRole;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductService {

    private final JwtService jwtService;
    private final WaiterService waiterService;
    private final UserService userService;
    private final ProductRepository productRepository;

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

        response.setAdminId(savedProduct.getAdmin().getId());
        response.setCreatedById(savedProduct.getCreatedBy().getId());

        return ResponseEntity.ok(response);
    }

    public String getProductById(String token){
        String tokenString = token.replace("Bearer ", "");
        Integer id = jwtService.extractClaimId(tokenString, "id");
        return "Product with ID: " + id;
    }
}
