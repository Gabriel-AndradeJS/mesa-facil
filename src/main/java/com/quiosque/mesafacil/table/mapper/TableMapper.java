package com.quiosque.mesafacil.table.mapper;

import com.quiosque.mesafacil.table.entity.TableEntity;
import com.quiosque.mesafacil.table.dtos.CreateTableDTO;
import com.quiosque.mesafacil.table.dtos.ResponseTableDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TableMapper {

    TableEntity dtoToEntity(CreateTableDTO dto);

    @Mapping(target = "createdByName", expression = "java(entity.getCreatedBy() != null ? entity.getCreatedBy().getName() : (entity.getAdmin() != null ? entity.getAdmin().getName() : null))")
    ResponseTableDTO entityToResponse(TableEntity entity);
}
