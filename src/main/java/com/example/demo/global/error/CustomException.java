package com.example.demo.global.error;

public class CustomException extends RuntimeException{

    private ErrorCode errorCode;
    private ErrorResponse errorResponse;

    public CustomException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorResponse = new ErrorResponse(errorCode);
    }

    public ErrorResponse getResponse(){
        return errorResponse;
    }

}
