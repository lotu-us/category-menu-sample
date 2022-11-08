package com.example.demo.domain.menu.repository;


import com.example.demo.domain.menu.domain.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("select menu from Menu menu where menu.categoryCode = :categoryCode and menu.menuCode = :menuCode")
    Optional<Menu> findByCategoryCodeAndMenuCode(@Param("categoryCode") String categoryCode, @Param("menuCode") Long menuCode);

    Page<Menu> findAllByCategoryCode(String categoryCode, Pageable pageable);
}
