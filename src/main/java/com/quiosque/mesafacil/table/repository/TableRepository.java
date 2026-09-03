package com.quiosque.mesafacil.table.repository;

import com.quiosque.mesafacil.table.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableRepository extends JpaRepository<TableEntity, Long> {

    TableEntity findByNumber(Integer number);

    Optional<TableEntity> findById(Long id);
}
