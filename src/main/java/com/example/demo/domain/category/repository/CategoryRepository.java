package com.example.demo.domain.category.repository;


import com.example.demo.domain.category.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryCode(String serviceCode);
    Page<Category> findAllByParentCategoryIsNull(Pageable pageable);
}
