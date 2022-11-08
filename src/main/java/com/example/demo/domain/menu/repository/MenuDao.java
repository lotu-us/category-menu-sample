package com.example.demo.domain.menu.repository;

import com.example.demo.domain.menu.domain.Menu;
import com.example.demo.global.error.CustomException;
import com.example.demo.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MenuDao {
    private final MenuRepository menuRepository;


    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu findByCategoryCodeAndMenuCode(String categoryCode, Long menuCode) {
        return menuRepository.findByCategoryCodeAndMenuCode(categoryCode, menuCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MENU));
    }

    public boolean checkDuplicateMenuCode(String categoryCode, Long menuCode) {
        return menuRepository.findByCategoryCodeAndMenuCode(categoryCode, menuCode).isEmpty();
    }

    public Page<Menu> findAllByCategoryCode(String categoryCode, Pageable pageable) {
        return menuRepository.findAllByCategoryCode(categoryCode, pageable);
    }

    public Page<Menu> findAll(Pageable pageable) {
        return menuRepository.findAll(pageable);
    }

    public void delete(Menu menu) {
        menuRepository.delete(menu);
    }


}
