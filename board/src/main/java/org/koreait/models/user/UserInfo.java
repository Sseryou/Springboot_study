package org.koreait.models.user;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
public class UserInfo implements UserDetails {
    //스프링에서 지원하는 기본 회원 툴

    private Long userNo;
    private String userId;
    private String userNm;
    private String userPw;
    private String email;
    private String mobile;

    private Collection<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userPw;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    //나중에 세부적으로 설정, 기본적 회원가입을 위해 일단 true
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정 잠금설정
    @Override
    public boolean isEnabled() {
        return true;
    }
}
