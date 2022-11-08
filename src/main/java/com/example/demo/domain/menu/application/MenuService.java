package com.example.demo.domain.menu.application;


import com.example.demo.domain.category.domain.Category;
import com.example.demo.domain.category.repository.CategoryDao;
import com.example.demo.domain.menu.domain.Menu;
import com.example.demo.domain.menu.dto.MenuDto;
import com.example.demo.domain.menu.repository.MenuDao;
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
public class MenuService {
    private final MenuDao menuDao;
    private final CategoryDao categoryDao;

    /**
     * create
     */
    public Object create(String categoryCode, MenuDto.CreateReq createReq){

        validateCreate(categoryCode, createReq);

        Category category = categoryDao.findByCategoryCode(categoryCode);

        Menu menu = Menu.builder()
                .menuCode(createReq.getMenuCode())
                .menuName(createReq.getMenuName())
                .description(createReq.getDescription())
                .category(category)
                .build();

        Menu saveMenu = menuDao.save(menu);

        MenuDto.Res response = MenuDto.Res.builder()
                .message("메뉴 정보를 추가하였습니다.")
                .data(saveMenu)
                .build();
        return response;
    }

    /**
     * create validate
     */
    private void validateCreate(String categoryCode, MenuDto.CreateReq createReq) {
        boolean isDuplicate = menuDao.checkDuplicateMenuCode(categoryCode, createReq.getMenuCode());
        if(isDuplicate == false){
            throw new CustomException(ErrorCode.DUPLICATE_MENU_CODE);
        }
    }


    /**
     * read
     */
    public Object read(String categoryCode, Long menuCode) {
        Menu menu = menuDao.findByCategoryCodeAndMenuCode(categoryCode, menuCode);

        MenuDto.Res response = MenuDto.Res.builder()
                .message("메뉴 정보를 조회하였습니다.")
                .data(menu)
                .build();
        return response;
    }


    /**
     * readListByCategory
     */
    public Object readListByCategory(String categoryCode, Pageable pageable) {
        Page<Menu> menu = menuDao.findAllByCategoryCode(categoryCode, pageable);

        MenuDto.Res response = MenuDto.Res.builder()
                .message("메뉴 정보 리스트를 조회하였습니다.")
                .data(menu)
                .build();
        return response;
    }

    /**
     * readList
     */
    public Object readList(Pageable pageable) {
        Page<Menu> menu = menuDao.findAll(pageable);

        MenuDto.Res response = MenuDto.Res.builder()
                .message("메뉴 정보 리스트를 조회하였습니다.")
                .data(menu)
                .build();
        return response;
    }

    /**
     * update
     */
    public Object update(String categoryCode, Long menuCode, MenuDto.UpdateReq updateReq) {
        Menu menu = menuDao.findByCategoryCodeAndMenuCode(categoryCode, menuCode);
        menu.update(updateReq);

        MenuDto.Res response = MenuDto.Res.builder()
                .message("메뉴 정보를 수정하였습니다.")
                .data(menu)
                .build();
        return response;
    }


    /**
     * delete
     */
    public Object delete(String categoryCode, Long menuCode) {
        Menu menu = menuDao.findByCategoryCodeAndMenuCode(categoryCode, menuCode);
        menuDao.delete(menu);

        MenuDto.Res response = MenuDto.Res.builder()
                .message("메뉴 정보를 삭제하였습니다.")
                .data(null)
                .build();
        return response;
    }


}
