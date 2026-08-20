package com.quiosque.mesafacil.user.repository;

import com.quiosque.mesafacil.user.entity.WaiterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaiterRepository extends JpaRepository<WaiterEntity, Long> {
    List<WaiterEntity> findAllByAdminId(Long adminId);

    WaiterEntity findByUserId(Long userId);
}
