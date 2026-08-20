package com.quiosque.mesafacil.mesa.dtos;

import com.quiosque.mesafacil.mesa.enums.Status;
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
