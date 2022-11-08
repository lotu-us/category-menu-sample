package com.example.demo.global.error;

public enum ErrorCode {
    
    NOT_FOUND_MENU("menuCode" , "menuCode에 맞는 Menu가 없습니다."),
    DUPLICATE_MENU_CODE("menuCode", "menuCode가 겹치는 Menu가 있습니다."),

    
    NOT_FOUND_CATEGORY_INFO("categoryCode", "categoryCode에 맞는 Category가 없습니다."),
    DUPLICATE_CATEGORY_CODE("categoryCode", "categoryCode가 겹치는 Category가 있습니다."),
    PLEASE_DELETE_CHILD_CATEGORY_FIRST("category", "삭제하려는 자식 카테고리를 먼저 지워주세요.")
    ;

    private String cause;
    private String code;
    private String message;
    private ErrorCode(String cause, String message) {
        this.cause = cause;
        this.code = this.name();
        this.message = message;
    }
    public String getCause() {
        return cause;
    }
    public String getCode() {
        return this.name();
    }
    public String getMessage() {
        return message;
    }


}
