package com.example.demo.domain.category.dto;

import com.example.demo.domain.category.domain.Category;
import com.example.demo.global.domain.BaseResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryDto {

    @Data
    public static class CreateReq{
        @NotBlank private String categoryCode;
        @NotBlank private String categoryName;
        private Long parentCategoryId;
    }

    @Data
    public static class ReadReq {
        @NotNull Boolean getParent;
        @NotNull Boolean getChild;
        @NotNull @Min(0) Integer depth;
    }

    @Data
    public static class UpdateReq{
        private String categoryName;
        private Long parentCategoryId;
    }






    /**
     * response에 관련된 것들
     */
    public static class Res extends BaseResponse {
        @Builder
        public Res(String message, Object data, boolean getParent, boolean getChild, Integer depth) {
            super(message, createJsonRes(data, getParent, getChild, depth));
        }

        private static Object createJsonRes(Object data, boolean getParent, boolean getChild, Integer depth) {
            if(depth == null){
                depth = Integer.MAX_VALUE;
            }

            if(data instanceof Category){
                return new JsonRes((Category) data, getParent, getChild, depth);
            }

            if(data instanceof Page){
                return new JsonPageRes((Page<Category>) data, getParent, getChild, depth);
            }

            return null;  //카테고리 삭제 시 null로 들어오면 처리해야함.
        }
    }


    /**
     * 순환참조를 방지하기 위해 따로 정의한다
     */
    @Data
    private static class JsonRes {
        private Long id;
        private String categoryCode;
        private String categoryName;
        private JsonRes parent;
        private List<JsonRes> childList;
        public JsonRes(Category category, boolean getParent, boolean getChild, Integer depth) {
            this.id = category.getId();
            this.categoryCode = category.getCategoryCode();
            this.categoryName = category.getCategoryName();

            if(depth > 0){
                if(getParent){
                    Category parent = category.getParentCategory();
                    if(parent != null){
                        this.parent = new JsonRes(parent, getParent,false, depth-1);
                    }
                }

                if(getChild){
                    List<Category> childList = category.getChildCategoryList();
                    if(childList != null){
                        this.childList = childList.stream()
                                .map(child -> new JsonRes(child, getParent, true, depth-1))
                                .collect(Collectors.toList());
                    }
                }
            }

        }
    }





    @Data
    private static class JsonPageRes {
        private List content;
        private int currentPage;
        private int totalPages;
        private int pageSize;
        private boolean firstPage;
        private boolean lastPage;

        @Builder
        public JsonPageRes(Page<Category> page, boolean getParent, boolean getChild, Integer depth) {
            List<JsonRes> jsonResList = page.getContent().stream()
                    .map(jsonRes -> new JsonRes(jsonRes, getParent, getChild, depth))
                    .collect(Collectors.toList());

            this.content = jsonResList;
            this.currentPage = page.getNumber()+1;
            this.totalPages = page.getTotalPages();
            this.pageSize = page.getSize();
            this.firstPage = page.isFirst();
            this.lastPage = page.isLast();
        }
    }


}

