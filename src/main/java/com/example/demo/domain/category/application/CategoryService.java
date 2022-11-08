package com.example.demo.domain.category.application;


import com.example.demo.domain.category.domain.Category;
import com.example.demo.domain.category.dto.CategoryDto;
import com.example.demo.domain.category.repository.CategoryDao;
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
public class CategoryService {
    private final CategoryDao categoryDao;

    /**
     * create
     */
    public Object create(CategoryDto.CreateReq createReq){
        validateCreate(createReq);

        Category parentService = null;
        if(createReq.getParentCategoryId() != null){
            parentService = categoryDao.findById(createReq.getParentCategoryId());
        }

        Category category = Category.builder()
                .categoryCode(createReq.getCategoryCode())
                .categoryName(createReq.getCategoryName())
                .parentCategory(parentService)
                .build();

        Category saved = categoryDao.save(category);

        CategoryDto.Res response = CategoryDto.Res.builder()
                .message("카테고리 정보를 추가했습니다.")
                .data(saved)
                .getParent(true)
                .build();

        return response;
    }

    private void validateCreate(CategoryDto.CreateReq createReq) {
        boolean isDuplicated = categoryDao.checkDuplicateCategoryCode(createReq.getCategoryCode());
        if(isDuplicated == false){
            throw new CustomException(ErrorCode.DUPLICATE_CATEGORY_CODE);
        }
    }

    /**
     * read
     */
    public Object read(String categoryCode, CategoryDto.ReadReq readReq){
        Category category = categoryDao.findByCategoryCode(categoryCode);

        CategoryDto.Res response = CategoryDto.Res.builder()
                .message("카테고리 정보를 조회하였습니다.")
                .data(category)
                .getParent(readReq.getGetParent())
                .getChild(readReq.getGetChild())
                .depth(readReq.getDepth())
                .build();
        return response;
    }

    /**
     * readList
     */
    public Object readList(boolean getChild, Pageable pageable){

        //parent가 없는 것이 1depth.
        Page<Category> parentPage = categoryDao.findAllByParentCategoryIsNull(pageable);

        CategoryDto.Res response = CategoryDto.Res.builder()
                .message("카테고리 정보를 조회하였습니다.")
                .data(parentPage)
                .getParent(false)
                .getChild(getChild)
                .build();
        return response;

    }

    /**
     * update
     */
    public Object update(String categoryCode, CategoryDto.UpdateReq updateReq){
        Category category = categoryDao.findByCategoryCode(categoryCode);

        Category parentCategory = categoryDao.findById(updateReq.getParentCategoryId());
        category.update(updateReq, parentCategory);

        CategoryDto.Res response = CategoryDto.Res.builder()
                .message("카테고리 정보를 수정하였습니다.")
                .data(category)
                .getParent(true)
                .build();
        return response;
    }

    /**
     * delete
     */
    public Object delete(String categoryCode){
        Category category = categoryDao.findByCategoryCode(categoryCode);

        if(category.getChildCategoryList() == null){
            categoryDao.delete(category);
        }else{
            throw new CustomException(ErrorCode.PLEASE_DELETE_CHILD_CATEGORY_FIRST);
        }

        CategoryDto.Res response = CategoryDto.Res.builder()
                .message("카테고리 정보를 삭제하였습니다.")
                .data(null)
                .build();
        return response;
    }

}
