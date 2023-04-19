package org.koreait.controllers;

import lombok.extern.java.Log;
import org.koreait.constants.MemberType;
import org.koreait.entities.Member;
import org.koreait.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log
public class Exam01Controller {
    //컨트롤 없이 데이터만 확인
    @Autowired
    private MemberRepository repository;

    @GetMapping("/ex01")
    public void ex01(){
        Member member = new Member();
        member.setUserId("user01");
        member.setUserPw("123456");
        member.setUserNm("사용자01");
        member.setType(MemberType.USER);

        Member member2 = repository.saveAndFlush(member); //영속성 추가 시 엔티티 반환
        log.info(member2.toString());

    }

    @GetMapping("/ex02")
    public void ex02(){
        //optinal <T>
        Member member = repository.findById(1L).orElse(null);
        log.info(member.toString());
    }

    @GetMapping("/ex03")
    public void ex03(){
        List<Member> members = new ArrayList<>();
        for (int i = 2; i <= 10; i++){
            Member member = new Member();
            member.setUserId("user" + i);
            member.setUserPw("123456");
            member.setUserNm("사용자" + i);
            member.setType(MemberType.USER);
            members.add(member);
        }



        //Iterable
        members = repository.saveAllAndFlush(members);

        members.stream().forEach(System.out::println);

    }

    @GetMapping("/ex04")
    public void ex04(){
        Member member = repository.findById(1L).orElse(null);

        member.setUserNm("(수정)사용자01");
        repository.flush();
        repository.delete(member);

        repository.flush();
    }

    @GetMapping("/ex05")
    public void ex05(){
        Member member = repository.findByUserId("user2");
        log.info(member.toString());
    }

}
