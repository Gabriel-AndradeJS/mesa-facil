package com.quiosque.mesafacil.User.Service;

import com.quiosque.mesafacil.User.DTOs.CreateUserDTO;
import com.quiosque.mesafacil.User.DTOs.ResponseUserDTO;
import com.quiosque.mesafacil.User.Mapper.UserMapper;
import com.quiosque.mesafacil.User.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private UserMapper mapper;

    public ResponseUserDTO createUser(CreateUserDTO dto){
        ResponseUserDTO response = mapper.createToResponse(dto);
        return response;
    }
}
