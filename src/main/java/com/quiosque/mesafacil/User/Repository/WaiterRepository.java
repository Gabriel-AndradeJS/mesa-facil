package com.quiosque.mesafacil.User.Repository;

import com.quiosque.mesafacil.User.Entity.WaiterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaiterRepository extends JpaRepository<WaiterEntity, Long> {
    List<WaiterEntity> findAllByAdminId(Long adminId);

    WaiterEntity findByUserId(Long userId);
}
