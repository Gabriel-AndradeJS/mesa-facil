package com.quiosque.mesafacil.table.repository;

import com.quiosque.mesafacil.table.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<TableEntity, Long> {

    TableEntity findByNumber(Integer number);
}
