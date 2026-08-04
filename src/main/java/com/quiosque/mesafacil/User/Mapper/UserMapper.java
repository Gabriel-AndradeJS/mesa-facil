package com.quiosque.mesafacil.User.Mapper;

import com.quiosque.mesafacil.User.DTOs.CreateUserDTO;
import com.quiosque.mesafacil.User.DTOs.ResponseUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

     ResponseUserDTO createToResponse(CreateUserDTO user);
}
