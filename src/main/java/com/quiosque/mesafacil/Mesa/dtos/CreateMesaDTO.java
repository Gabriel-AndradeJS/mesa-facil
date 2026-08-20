package com.quiosque.mesafacil.Mesa.dtos;

import com.quiosque.mesafacil.Mesa.Enums.Status;
import com.quiosque.mesafacil.User.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateMesaDTO {

    private String titular;

    private Integer number;

    private Status status;

    //private UserEntity createdBy;

    //private UserEntity admin;
}
