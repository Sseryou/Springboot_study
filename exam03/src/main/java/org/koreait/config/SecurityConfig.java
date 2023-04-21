package org.koreait.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean //관리객체로 넣어줘야 한다.
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //인증 관련은 'http'에서 처리


        return http.build();
    }

}
