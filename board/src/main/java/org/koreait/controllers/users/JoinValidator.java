package org.koreait.controllers.users;

import lombok.RequiredArgsConstructor;
import org.koreait.commons.validators.MobileValidator;
import org.koreait.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator, MobileValidator {
    //Validator는 스프링에 달린걸로..
    private final UserRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return JoinForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) { //BeanValidation의 검증 실패가 있는 경우
            return;
        }
        JoinForm joinForm = (JoinForm) target;

        String userId = joinForm.getUserId();
        String userPw = joinForm.getUserPw();
        String userPwRe = joinForm.getUserPwRe();
        String mobile = joinForm.getMobile();


        // 1. 아이디 중복 여부
        if(repository.userExist(userId)){
            errors.rejectValue("userId", "Validation.duplicate.userId");
        }
        // 2. userPw, userPwRe 일치 여부
        if(!userPw.equals(userPwRe)){
            errors.rejectValue("userPwRe", "Validation.incorrect.userPw");
        }

        // 3. 휴대전화번호(선택) 가 있으면 형식 체크
        if(mobile != null && !mobile.isBlank()){
            if(!mobildCheck(mobile)){ //휴대전화번호 형식이 아닌 경우
                errors.rejectValue("mobile", "Validation.mobile");
            }
            //이 구문에서 mobile에 들어있는 다른것들을 제거하고 숫자만 남긴다.
            mobile = mobile.replaceAll("\\D", "");

            joinForm.setMobile(mobile);
        }

    }
}
