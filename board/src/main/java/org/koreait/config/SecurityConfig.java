package org.koreait.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.koreait.models.user.LoginFailureHandler;
import org.koreait.models.user.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.formLogin()
                .loginPage("/user/login") //로그인 페이지 url
                //.defaultSuccessUrl("/")  //성공시 이동할 url
                .successHandler(new LoginSuccessHandler())
                .usernameParameter("userId")
                .passwordParameter("userPw")
                //.failureUrl("/user/login") //로그인 실패시 이동할 URL
                .failureHandler(new LoginFailureHandler()) //유저에게 무엇이 틀렸는가를 알려주기 위한 구문
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/user/login"); //로그아웃 성공시 이동할 URL

        http.authorizeHttpRequests()
                .requestMatchers("/mypage/**").authenticated() //로그인한 회원만 가능한 URL 패턴
                .requestMatchers("/admin/**").hasAuthority("ADMIN") //관리자만 접근 가능한 URL 패턴
                .anyRequest().permitAll();
        // 관리자 접근 권한 없을시 -> 접근 권한 없습니다. 401 - Unauthorized
        // 비회원 접근 권한 없는 경우 -> 로그인 페이지

        http.exceptionHandling()
                .authenticationEntryPoint((req, res, e) -> {
                    String redirectUrl = "/user/login";
                    String URI = req.getRequestURI();
                    if(URI.indexOf("/admin") != -1){ //관리자 페이지
                        redirectUrl ="/error/401";
                    }

                    res.sendRedirect(redirectUrl);
                });

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
       return w -> w.ignoring()
               .requestMatchers("/css/**", "/js/**", "images/**", "/api/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        //스프링은 BCrypt도 지원한다....
        return new BCryptPasswordEncoder();
    }


}
