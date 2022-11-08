package com.example.demo.domain.category.repository;


import com.example.demo.domain.category.domain.Category;
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
public class CategoryDao {
    private final CategoryRepository categoryRepository;

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CATEGORY_INFO));
    }

    public Category findByCategoryCode(String categoryCode){
        return categoryRepository.findByCategoryCode(categoryCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CATEGORY_INFO));
    }

    public boolean checkDuplicateCategoryCode(String categoryCode) {
        return categoryRepository.findByCategoryCode(categoryCode).isEmpty();
    }

    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }


    public Page<Category> findAllByParentCategoryIsNull(Pageable pageable) {
        return categoryRepository.findAllByParentCategoryIsNull(pageable);
    }
}
