package org.koreait.controllers;


import lombok.RequiredArgsConstructor;
import org.koreait.entities.BoardData;
import org.koreait.entities.Member;
import org.koreait.repositories.BoardDataRepository;
import org.koreait.repositories.MemberRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/exam03")
@RequiredArgsConstructor
public class Exam03Controller {

    private final MemberRepository memberRepository;
    private final BoardDataRepository repository;

    @GetMapping("/ex01")
    public void ex01(){
        Member member = new Member();
        member.setUserId("user01");
        member.setUserPw("123456");
        member.setUserNm("사용자01");
        //memberRepository에다가 member 저장 후 member에 덮어쓰기
        member = memberRepository.saveAndFlush(member);

        List<BoardData> datas = new ArrayList<>();

        for(int i = 1; i <= 10; i++){
            BoardData data = new BoardData();
            data.setSubject("제목" + i);
            data.setContent("내용" + i);
            data.setMember(member);

            datas.add(data);
        }
        repository.saveAllAndFlush(datas);

    }

}
