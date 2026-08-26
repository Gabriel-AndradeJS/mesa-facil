package com.quiosque.mesafacil.mesa.repository;

import com.quiosque.mesafacil.mesa.entity.MesaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MesaRepository extends JpaRepository<MesaEntity, Long> {

    MesaEntity findByNumber(Integer number);
}
