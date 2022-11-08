package com.example.demo.domain.category.domain;


import com.example.demo.domain.category.dto.CategoryDto;
import com.example.demo.global.domain.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name = "category")
@DynamicUpdate
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint comment '아이디'")
    private Long id;

    @Column(name = "category_code", columnDefinition = "varchar(255) comment '카테고리 코드'")
    private String categoryCode;

    @Column(name = "category_name", columnDefinition = "varchar(255) comment '카테고리 이름'")
    private String categoryName;

    /**
     * 자기 자신 맵핑
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id", referencedColumnName = "id", columnDefinition = "bigint comment '부모 카테고리 아이디'")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Category> childCategoryList = new ArrayList<>();



    public void update(CategoryDto.UpdateReq updateReq, Category parentCategory) {
        this.categoryName = updateReq.getCategoryName();
        this.parentCategory = parentCategory;
    }



}
