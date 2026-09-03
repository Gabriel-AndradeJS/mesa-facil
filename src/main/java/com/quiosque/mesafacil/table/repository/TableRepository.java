package com.quiosque.mesafacil.table.repository;

import com.quiosque.mesafacil.table.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface TableRepository extends JpaRepository<TableEntity, Long> {

    Optional<TableEntity> findByNumberAndAdminId(Integer number, Long adminId);

    List<TableEntity> findAllByAdminId(Long adminId);

    Optional<TableEntity> findByIdAndAdminId(Long id, Long adminId);

}
