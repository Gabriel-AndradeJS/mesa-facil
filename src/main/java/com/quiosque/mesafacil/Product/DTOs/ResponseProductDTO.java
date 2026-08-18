package com.quiosque.mesafacil.Product.DTOs;

import com.quiosque.mesafacil.Product.Enums.ProductEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProductDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private ProductEnum status;

    private Long adminId;
    private Long createdById;
}