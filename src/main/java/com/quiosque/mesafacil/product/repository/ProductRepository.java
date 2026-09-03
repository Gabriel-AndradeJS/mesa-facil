package com.quiosque.mesafacil.product.repository;

import com.quiosque.mesafacil.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("""
    SELECT p FROM ProductEntity p
    WHERE p.admin.id = :id
""")
    List<ProductEntity> findAllProduct(
             Long id
    );

    @Query("""
    SELECT p
    FROM ProductEntity p
    WHERE p.mesaId.id = :mesaId
""")
    List<ProductEntity> findAllByMesaId(@Param("mesaId") Long mesaId);
}
