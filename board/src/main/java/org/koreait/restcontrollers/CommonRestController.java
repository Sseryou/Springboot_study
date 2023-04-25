package org.koreait.restcontrollers;

import org.koreait.commons.rest.JSONResult;
import org.koreait.commons.rest.RestCommonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("org.koreait.restcontrollers")
public class CommonRestController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSONResult<Object>> errorHandler(Exception e){
        JSONResult<Object> jsonResult = new JSONResult<>();
        jsonResult.setSuccess(false);
        jsonResult.setErrorMessage(e.getMessage());


        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; //기본값은 500
        if(e instanceof RestCommonException){ //RestCommonException인 경우는 상태 코드 조회
            RestCommonException restE = (RestCommonException) e;
            status = restE.getStatus();
        }
        return ResponseEntity.status(status).body(jsonResult);
    }
}
