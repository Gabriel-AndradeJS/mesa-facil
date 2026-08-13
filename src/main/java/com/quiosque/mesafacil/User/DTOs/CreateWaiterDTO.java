package com.quiosque.mesafacil.User.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateWaiterDTO {

    private String name;
    private String email;
    private String password;
}
