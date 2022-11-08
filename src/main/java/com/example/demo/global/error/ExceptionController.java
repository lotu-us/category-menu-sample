package com.example.demo.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NonUniqueResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity customExceptionHandler(CustomException exception){
        log.warn("==================[ CustomException ]========================");
        log.warn("" + exception.getResponse().toString());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getResponse());
    }


    /**
     * controller에 있는 값을 검증하고
     * 에러가 있다면 MethodArgumentNotValidException이 발생된다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity validExceptionHandler(MethodArgumentNotValidException exception){
        log.warn("==================[ Controller Parameter Validation Error ]========================");
        ErrorResponse errorResponse = ErrorResponse.builder()
                .cause(exception.getBindingResult().getFieldError().getField())
                .code("validate")
                .message(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .build();

        log.warn("" + errorResponse.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * controller 제외한 service, entity..에 있는 값을 검증하고
     * 에러가 있다면 constraintViolationExceptionHandler이 발생된다.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity constraintViolationExceptionHandler(ConstraintViolationException exception){
        log.warn("==================[ Service or Entity Validation Error ]========================");
        List<String> causes = new ArrayList<>();
        List<String> messages = new ArrayList<>();

        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            causes.add(constraintViolation.getPropertyPath().toString());
            messages.add(constraintViolation.getMessage());
        }

        ErrorResponse errorResponse = ErrorResponse.builder()
                .cause(causes.get(0))
                .code("validate")
                .message(messages.get(0))
                .build();

        log.warn("" + errorResponse.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * @RequestParam 값을 검증한다
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity missingRequestParamExceptionHandler(MissingServletRequestParameterException exception){
        log.warn("==================[ @RequestParam Validation Error ]========================");

        ErrorResponse errorResponse = ErrorResponse.builder()
                .cause(exception.getParameterType() + " " + exception.getParameterName())
                .code("validate")
                .message(exception.getLocalizedMessage())
                .build();

        log.warn("" + errorResponse.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }



}
