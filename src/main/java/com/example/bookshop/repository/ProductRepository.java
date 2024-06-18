package com.example.bookshop.repository;

import com.example.bookshop.entity.product.CategoryEntity;
import com.example.bookshop.entity.product.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Page<ProductEntity> findAllByCategory(CategoryEntity category, Pageable pageable);

    Page<ProductEntity> findAllByNameContaining(String name, Pageable pageable);

    Page<ProductEntity> findAll(Pageable pageable);


}
