package org.koreait.restcontrollers.member;

import lombok.extern.java.Log;
import org.koreait.models.member.User;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log
@RestController
@RequestMapping("/api")
public class ApiMemberController {
    @GetMapping("/member")
    public User member(){
        User user = User.builder()
                .userNo(1L)
                .userId("user01")
                .userPw("123456")
                .userNm("사용자01")
                //.regDt(LocalDateTime.now())
                .build();

        return user;
    }

    @GetMapping("/members")
    public List<User> members(){
        List<User> members = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            User user = User.builder()
                    .userNo(Long.valueOf(i))
                    .userId("user" + i)
                    .userPw("123456")
                    .userNm("사용자" + i)
                    //.regDt(LocalDateTime.now())
                    .build();

           members.add(user);

        }
        return members;
    }

    @PostMapping("/join")
    public void join(@RequestBody User user){
        log.info(user.toString());
    }



}
