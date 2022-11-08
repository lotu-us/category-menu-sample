package com.example.demo.domain.menu.dto;


import com.example.demo.global.domain.BaseResponse;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MenuDto {

    @Data
    public static class CreateReq{
        @NotNull private Long menuCode;
        @NotBlank private String menuName;
        @NotBlank private String description;
    }


    @Data
    public static class UpdateReq{
        private String menuName;
        private String description;
    }


    public static class Res extends BaseResponse {
        @Builder
        public Res(String message, Object data) {
            super(message, data);
        }
    }

}
