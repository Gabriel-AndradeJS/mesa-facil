package com.quiosque.mesafacil.table.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseTableDTO {


    private Long id;
    private String titular;
    private Integer number;
    private String status;
    private String createdByName;
}
