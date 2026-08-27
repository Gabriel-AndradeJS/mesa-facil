package com.quiosque.mesafacil.table.repository;

import com.quiosque.mesafacil.table.entity.MesaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MesaRepository extends JpaRepository<MesaEntity, Long> {

    MesaEntity findByNumber(Integer number);
}
