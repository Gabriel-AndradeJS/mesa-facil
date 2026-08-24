package com.quiosque.mesafacil.mesa.mapper;

import com.quiosque.mesafacil.mesa.entity.MesaEntity;
import com.quiosque.mesafacil.mesa.dtos.CreateMesaDTO;
import com.quiosque.mesafacil.mesa.dtos.ResponseMesaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MesaMapper {

    MesaEntity dtoToEntity(CreateMesaDTO dto);

    @Mapping(target = "createdByName", expression = "java(entity.getCreatedBy() != null ? entity.getCreatedBy().getName() : (entity.getAdmin() != null ? entity.getAdmin().getName() : null))")
    ResponseMesaDTO entityToResponse(MesaEntity entity);
}