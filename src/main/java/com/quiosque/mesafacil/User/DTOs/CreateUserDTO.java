package com.quiosque.mesafacil.User.DTOs;

import com.quiosque.mesafacil.User.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserDTO {

    private String name;

    private String email;

    private String password;

    private UserRole role;
}