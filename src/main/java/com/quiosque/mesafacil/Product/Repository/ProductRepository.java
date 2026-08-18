package com.quiosque.mesafacil.Product.Repository;

import com.quiosque.mesafacil.Product.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
