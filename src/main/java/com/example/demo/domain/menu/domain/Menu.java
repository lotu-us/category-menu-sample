package com.example.demo.domain.menu.domain;

import com.example.demo.domain.category.domain.Category;
import com.example.demo.domain.menu.dto.MenuDto;
import com.example.demo.global.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "menu")
@DynamicUpdate
public class Menu extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint comment '아이디'")
    private Long id;

    @Column(name = "menu_code", columnDefinition = "bigint comment '메뉴코드'")
    private Long menuCode;
    @Column(name = "menu_name", columnDefinition = "varchar(255) comment '메뉴명'")
    private String menuName;
    @Column(name = "description", columnDefinition = "text comment '메뉴설명'")
    private String description;


    @JsonIgnore
    @Getter(AccessLevel.PRIVATE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", columnDefinition = "bigint comment '카테고리 아이디'")
    private Category category;

    @Column(name = "category_code", columnDefinition = "varchar(255) comment '카테고리 코드'")
    private String categoryCode;

    /**
     * @Transient
     * DB에 등록하지 않고 사용하는 컬럼
     */
    @Transient
    private Long categoryId;

    @Transient
    private String categoryName;



    public void update(MenuDto.UpdateReq updateReq) {
        this.menuName = updateReq.getMenuName();
        this.description = updateReq.getDescription();
        super.setModifiedDateTime(LocalDateTime.now());
    }


    @Builder
    public Menu(Long id, Long menuCode, String menuName, String description, Category category) {
        this.id = id;
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.description = description;
        this.category = category;
        this.categoryId = category.getId();
        this.categoryCode = category.getCategoryCode();
        this.categoryName = category.getCategoryName();
    }

    public String getCategoryCode() {
        return this.category.getCategoryCode();
    }

    public Long getCategoryId() {
        return this.category.getId();
    }

    public String getCategoryName() {
        return this.category.getCategoryName();
    }
}
