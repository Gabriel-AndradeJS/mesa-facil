package com.quiosque.mesafacil.User.Service;

import com.quiosque.mesafacil.User.DTOs.CreateUserDTO;
import com.quiosque.mesafacil.User.DTOs.ResponseUserDTO;
import com.quiosque.mesafacil.User.Entity.UserEntity;
import com.quiosque.mesafacil.User.Mapper.UserMapper;
import com.quiosque.mesafacil.User.Repository.UserRepository;
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
}
