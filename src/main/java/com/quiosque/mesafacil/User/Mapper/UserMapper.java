package com.quiosque.mesafacil.User.Mapper;

import com.quiosque.mesafacil.User.DTOs.CreateUserDTO;
import com.quiosque.mesafacil.User.DTOs.ResponseUserDTO;
import com.quiosque.mesafacil.User.Entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

     ResponseUserDTO createToResponse(CreateUserDTO user);

     UserEntity createToEntity(CreateUserDTO user);

     ResponseUserDTO EntityToResponse(UserEntity user);
}
