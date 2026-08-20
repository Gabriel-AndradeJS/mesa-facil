package com.quiosque.mesafacil.Mesa.mapper;

import com.quiosque.mesafacil.Mesa.Entity.MesaEntity;
import com.quiosque.mesafacil.Mesa.dtos.CreateMesaDTO;
import com.quiosque.mesafacil.Mesa.dtos.ResponseMesaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.TargetType;

@Mapper(componentModel = "spring")
public interface MesaMapper {

    MesaEntity dtoToEntity(CreateMesaDTO dto);

    @Mapping(target = "createdByName", expression = "java(entity.getCreatedBy() != null ? entity.getCreatedBy().getName() : (entity.getAdmin() != null ? entity.getAdmin().getName() : null))")
    ResponseMesaDTO entityToResponse(MesaEntity entity);
}