package com.quiosque.mesafacil.table.mapper;

import com.quiosque.mesafacil.table.entity.MesaEntity;
import com.quiosque.mesafacil.table.dtos.CreateMesaDTO;
import com.quiosque.mesafacil.table.dtos.ResponseMesaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MesaMapper {

    MesaEntity dtoToEntity(CreateMesaDTO dto);

    @Mapping(target = "createdByName", expression = "java(entity.getCreatedBy() != null ? entity.getCreatedBy().getName() : (entity.getAdmin() != null ? entity.getAdmin().getName() : null))")
    ResponseMesaDTO entityToResponse(MesaEntity entity);
}
