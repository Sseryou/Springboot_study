package org.koreait.restcontrollers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.koreait.commons.rest.JSONResult;
import org.koreait.commons.rest.RestCommonException;
import org.koreait.controllers.users.JoinForm;
import org.koreait.controllers.users.JoinValidator;
import org.koreait.entities.Users;
import org.koreait.models.user.UserSaveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Log
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class ApiUserController {

    private final UserSaveService service;
    private final JoinValidator validator;

    @PostMapping("/account")
    public ResponseEntity<Object> account(@RequestBody @Valid JoinForm joinForm, Errors errors){

        validator.validate(joinForm, errors);

        ResourceBundle bundle = ResourceBundle.getBundle("messages.validations");

        if(errors.hasErrors()){
           List<String> errMessages = errors.getAllErrors().stream().map(e -> {
                List<String> messages = new ArrayList<>();
               String[] codes = e.getCodes();
               for(String code : codes){
                   String msg = null;
                   try {
                       //없는 항목을 조회할수도 있음.
                       msg = (bundle.getString(code));
                   }catch (Exception e2){
                       msg = (e.getDefaultMessage());
                   }
                   if(msg != null && !msg.isBlank()){
                       messages.add(msg);
                   }
               }
               return messages.stream().collect(Collectors.joining(","));
            }).toList();
           String errMessage = errMessages.stream().collect(Collectors.joining(","));
           throw new RestCommonException(errMessage, HttpStatus.BAD_REQUEST);
        }

        service.save(joinForm);

        return ResponseEntity.ok().build(); //성공시는 응답코드 200, body데이터 x
    }



}
