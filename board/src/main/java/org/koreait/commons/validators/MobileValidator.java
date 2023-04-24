package org.koreait.commons.validators;

public interface MobileValidator {

    default boolean mobildCheck(String mobile){
        //1. 형식을 통일화(숫자만 남긴다...)
        //2. 형식을 체크
        //숫자만 남기게 된다.
        mobile = mobile.replaceAll("\\D", "");

        //010,011,016만 감지,3자리 또는 4자리,마지막은 4자리
        String pattern = "^01[016]\\d{3,4}\\d{4}";


        return mobile.matches(pattern);
    }
}
