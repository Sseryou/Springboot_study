package org.koreait.controllers;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Member;
import org.koreait.repositories.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/exam02")
@RequiredArgsConstructor
public class Exam02Controller {

    private final MemberRepository repository;


    @GetMapping("/ex01")
    public List<Member> ex01(){
        List<Member> members = repository.findByUserIdNot("user2");
        return members;
    }

    @GetMapping("/ex02")
    public List<Member> ex02(){
        List<Member> members = repository.findByUserNmContaining("자");
        return members;
    }

    @GetMapping("/ex03")
    public List<Member> ex03(){
        LocalDate today = LocalDate.now();
        Pageable pageable = PageRequest.of(0,10, Sort.by(Sort.Order.desc("regDt"),
                Sort.Order.asc("userId")));
        List<Member> members = repository.findByRegDtBetween(today.minusDays(3),
                                          today.plusDays(1), pageable);
        return members;
    }

    @GetMapping("/ex04")
    public List<Member> ex04(){
        LocalDate today = LocalDate.now();
        List<Member> members = repository.findByRegDtBetweenOrderByRegDtDesc(today.minusDays(3),
                                today.plusDays(1));
        return members;
    }

    @GetMapping("/ex05")
    public List<Member> ex05(){
        //페이징 : 게시판 하단 부분 보면 다른 정보를 보여줄 수 있는 1, 2, 3, 4, 5...같은 버튼들이 있다.
        //그 버튼을 눌러 다른 페이지를 보여주는 것을 페이징
        //pageable의 시작 : 0번부터...
        //페이지와 사이즈...

        Pageable pageable = PageRequest.of(0,10,Sort.by(Sort.Order.desc("regDt")));
        //Page가 들어가면, 반환값이 Page<>이다.
        //Page = 페이지에 관련된 데이터가 들어있음. 멤버리스트..이전페이지..다음페이지 등등...
        Page<Member> page = repository.findAll(pageable);
        List<Member> members = page.getContent();
        return members;
    }

    @GetMapping("/ex06")
    public List<Member> ex06(){
        List<Member> members = repository.findByUsers("용");
        return members;
    }

    @GetMapping("/ex07")
    public List<Member> ex07(){
        List<Member> members = repository.findUsers("용");
        return members;
    }



}
