package org.koreait.models.user;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Users;
import org.koreait.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInfoService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //회원정보 조회에 관련된 메서드
        //이미 예외가 추가되어 있다. 그 예외를 if문으로 사용
        Users user = repository.findByUserId(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority(user.getType().toString())); //한명이 여러권한을 갖는경우


        return UserInfo.builder()
                .userNo(user.getUserNo())
                .userId(user.getUserId())
                .userPw(user.getUserPw())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .authorities(authorities)
                .build();
    }
}
