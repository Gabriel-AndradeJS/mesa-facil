package com.quiosque.mesafacil.user.mapper;

import com.quiosque.mesafacil.user.dto.CreateUserDTO;
import com.quiosque.mesafacil.user.dto.ResponseUserDTO;
import com.quiosque.mesafacil.user.dto.WaiterDTO;
import com.quiosque.mesafacil.user.entity.UserEntity;
import com.quiosque.mesafacil.user.entity.WaiterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

     ResponseUserDTO createToResponse(CreateUserDTO user);

     UserEntity createToEntity(CreateUserDTO user);

     ResponseUserDTO EntityToResponse(UserEntity user);

     @Mapping(target = "accountNonExpired", expression = "java(user.isAccountNonExpired())")
     @Mapping(target = "accountNonLocked", expression = "java(user.isAccountNonLocked())")
     @Mapping(target = "credentialsNonExpired", expression = "java(user.isCredentialsNonExpired())")
     @Mapping(target = "enabled", expression = "java(user.isEnabled())")
     @Mapping(target = "authorities", expression = "java(new com.quiosque.mesafacil.User.DTOs.AuthoritiesDTO(user.getAuthorities().stream().findFirst().map(a -> a.getAuthority()).orElse(null)))")
     WaiterDTO UserEntityToWaiter(UserEntity user);

     @Mapping(target = "name", source = "user.name")
     @Mapping(target = "email", source = "user.email")
     @Mapping(target = "role", source = "user.role")
     @Mapping(target = "createdAt", source = "user.createdAt")
     @Mapping(target = "userId", source = "user.id")
     @Mapping(target = "adminId", source = "admin.id")
     @Mapping(target = "accountNonExpired", expression = "java(waiter.getUser().isAccountNonExpired())")
     @Mapping(target = "accountNonLocked", expression = "java(waiter.getUser().isAccountNonLocked())")
     @Mapping(target = "credentialsNonExpired", expression = "java(waiter.getUser().isCredentialsNonExpired())")
     @Mapping(target = "enabled", expression = "java(waiter.getUser().isEnabled())")
     @Mapping(target = "authorities", expression = "java(new com.quiosque.mesafacil.User.DTOs.AuthoritiesDTO(waiter.getUser().getAuthorities().stream().findFirst().map(a -> a.getAuthority()).orElse(null)))")
     WaiterDTO WaiterEntityToWaiter(WaiterEntity waiter);

}
