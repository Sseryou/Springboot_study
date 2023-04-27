package org.koreait.config;

import org.koreait.models.user.UserInfo;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        /**
         * null값 체크를 할 수 없다. 로그인을 하지 않아도 비회원이란 정보(문자열)가 들어가기 때문
         * principal -> String : 비회원(Anonymous)
         *          -> UserDetails 구현체
         */
        String userId = null;
        if(principal instanceof UserInfo){
            UserInfo userInfo = (UserInfo)principal;
            userId = userInfo.getUserId();
        }

        return Optional.ofNullable(userId);
    }
}
