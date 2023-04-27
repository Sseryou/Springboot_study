package org.koreait.controllers.mypage;

import lombok.extern.java.Log;
import org.koreait.entities.BoardData;
import org.koreait.models.user.UserInfo;
import org.koreait.repositories.BoardDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Log
@Controller
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    private BoardDataRepository repository;

    @GetMapping
    public String index(){
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        UserInfo userInfo = (UserInfo)principal;
        log.info(userInfo.toString());

        return "mypage/index";
    }



    /**
    @GetMapping
    public String index(Principal principal){
        String userId = principal.getName();//username -> 아이디

        log.info(userId);
        return "mypage/index";
    }
    */

    /**
    @GetMapping
    public String index(@AuthenticationPrincipal UserInfo userInfo){
        log.info(userInfo.toString());
        return "mypage/index";
    }
    */

    @GetMapping("/exam")
    @ResponseBody
    public void exam(){
        BoardData data = BoardData.builder()
                .subject("제목")
                .content("내용")
                .build();
        data = repository.saveAndFlush(data);

        data.setSubject("(수정)제목");

        repository.flush();

    }

}
