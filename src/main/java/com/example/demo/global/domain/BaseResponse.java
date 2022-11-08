package com.example.demo.global.domain;


import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.Map;

import static com.example.demo.global.config.ObjectMapperConfig.objectMapper;

@Getter
public class BaseResponse {
    private String message;
    private Map<String, Object> data;

    public BaseResponse(String message, Object data) {
        this.message = message;

        if(data instanceof Page){
            data = new PageResponse((Page) data);
        }
        this.data = objectMapper.convertValue(data, Map.class);
    }
}
