package com.quiosque.mesafacil.User.Service;

import com.quiosque.mesafacil.User.DTOs.CreateUserDTO;
import com.quiosque.mesafacil.User.DTOs.ResponseUserDTO;
import com.quiosque.mesafacil.User.Mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class UserService {

    private UserMapper mapper;

    public ResponseUserDTO createUser(CreateUserDTO dto){
        log.info("Creating user with Senha: {}", dto.getPassword());
        return mapper.createToResponse(dto);
    }
}
