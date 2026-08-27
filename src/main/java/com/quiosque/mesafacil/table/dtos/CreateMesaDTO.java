package com.quiosque.mesafacil.table.dtos;

import com.quiosque.mesafacil.table.enums.Status;
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
