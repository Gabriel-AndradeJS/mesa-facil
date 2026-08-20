package com.quiosque.mesafacil.user.service;

import com.quiosque.mesafacil.configs.JwtService;
import com.quiosque.mesafacil.user.dto.CreateUserDTO;
import com.quiosque.mesafacil.user.dto.ResponseUserDTO;
import com.quiosque.mesafacil.user.dto.WaiterDTO;
import com.quiosque.mesafacil.user.entity.UserEntity;
import com.quiosque.mesafacil.user.mapper.UserMapper;
import com.quiosque.mesafacil.user.repository.UserRepository;
import com.quiosque.mesafacil.user.repository.WaiterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class UserService {

    private UserMapper mapper;
    private final UserRepository userRepository;
    private final WaiterRepository waiterRepository;
    private final JwtService jwtService;

    public ResponseEntity<ResponseUserDTO> createUser(CreateUserDTO dto){
        UserEntity user = UserEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(dto.getRole())
                .build();
        UserEntity savedUser = userRepository.save(user);
        ResponseUserDTO responseUserDTO = mapper.EntityToResponse(savedUser);
        return ResponseEntity.ok(responseUserDTO);
    }

    public List<ResponseUserDTO> getUsers(){
        return userRepository.findAll().stream()
                .map(mapper::EntityToResponse)
                .toList();
    }

    public  List<WaiterDTO> getWaiters(String token){
        String tokenString = token.replace("Bearer ", "");
        Integer id = jwtService.extractClaimId(tokenString, "id");

        UserEntity adminUser = userRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Admin user not found"));

        return waiterRepository.findAllByAdminId(adminUser.getId()).stream()
                .map(mapper::WaiterEntityToWaiter)
                .toList();
    }

    public UserEntity getUserById(Long id){
        return userRepository.findById(id).orElseThrow( () -> new RuntimeException("User not found"));
    }
}
