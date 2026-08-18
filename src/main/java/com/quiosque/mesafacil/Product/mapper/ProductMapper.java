package com.quiosque.mesafacil.Product.mapper;

import com.quiosque.mesafacil.Product.DTOs.ResponseProductDTO;
import com.quiosque.mesafacil.Product.Entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class ProductMapper {

    public ResponseProductDTO productToResponse(ProductEntity product) {
        ResponseProductDTO response = new ResponseProductDTO();

        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setStatus(product.getStatus());

        if (product.getCreatedBy() != null) {
            response.setWaiterName(product.getCreatedBy().getName());
            response.setCreatedById(product.getCreatedBy().getId());
        } else if (product.getAdmin() != null) {
            response.setWaiterName(product.getAdmin().getName());
            response.setCreatedById(product.getAdmin().getId());
        }

        return response;
    }
}