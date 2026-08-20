package com.quiosque.mesafacil.product.dto;

import com.quiosque.mesafacil.product.enums.ProductEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateProductDTO {

    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private ProductEnum status;
    private Long waiterId;
    private Long adminId;
}
