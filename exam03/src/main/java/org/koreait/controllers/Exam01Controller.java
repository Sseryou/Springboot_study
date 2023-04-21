package org.koreait.controllers;


import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.koreait.entities.BoardData;
import org.koreait.entities.Member;
import org.koreait.entities.MemberAddress;
import org.koreait.entities.QBoardData;
import org.koreait.repositories.BoardDataRepository;
import org.koreait.repositories.MemberAddressRepository;
import org.koreait.repositories.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@Log
@RestController
@RequiredArgsConstructor
public class Exam01Controller {

    private final BoardDataRepository boardDataRepository;

    private final MemberRepository memberRepository;

    private final MemberAddressRepository memberAddressRepository;
    private final EntityManager em;


    @GetMapping("/ex01")
    public void ex01(){
        Member member = Member.builder()
                .userId("user01")
                .userPw("123456")
                .userNm("사용자01")
                .mobile("01000000000")
                .email("user01@user.org")
                .build();

        member = memberRepository.saveAndFlush(member);

        List<BoardData> datas = new ArrayList<>();
        for (int i = 1; i <= 10; i++){
            BoardData data = BoardData.builder()
                    .subject("제목"+ i)
                    .content("내용" + i)
                    .member(member)
                    .build();
            datas.add(data);
        }

        boardDataRepository.saveAllAndFlush(datas);
    }

    @GetMapping("/ex02")
    public void ex02(){
        BoardData data = boardDataRepository.findById(1L).orElse(null);
        //BoardData 엔티티만 조회
        /**
        log.info(data.toString());
        Member member = data.getMember();
        log.info(member.toString());
         */
        Member member = data.getMember();
        String userId = member.getUserId(); //쿼리 실행
        log.info(userId);

    }

    @GetMapping("/ex03")
    public void ex03(){
        Member member = memberRepository.findById(1L).orElse(null);
        List<BoardData> boardData = member.getBoardData();

        //그냥 스트림을 쓰면 내부 getter 호출 때문에 순환참조 발생. 해결 해 주고 실행
        boardData.stream().forEach(System.out::println);
    }

    @GetMapping("/ex04")
    public void ex04(){
        //처음 1번의 호출
        List<BoardData> boardDatas = boardDataRepository.findAll();
        for(BoardData boardData : boardDatas){
            Member member = boardData.getMember();
            String userId = member.getUserId();
            //처음 1번의 호출 + 10번의 이름 호출 = 쓸데없는 성능저하
            //선택적으로 즉시 로딩을 사용하여 해결 할 수 있다.
            String userNm = member.getUserNm();
            log.info("userId= " + userId + ", userNm= " + userNm);
        }
    }


    @GetMapping("/ex05")
    public void ex05(){
        List<BoardData> boardDatas = boardDataRepository.findBoardDatas();
    }

    @GetMapping("/ex06")
    @Transactional //일시적인 데이터로 만든다.
    public void ex06(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QBoardData boardData = QBoardData.boardData;
        JPAQuery<BoardData> query = queryFactory.selectFrom(boardData)
                .leftJoin(boardData.member)
                .orderBy(boardData.regDt.desc())
                .fetchJoin();
        //fetchJoin

        List<BoardData> datas = query.fetch();
        datas.stream().forEach(System.out::println);
    }

    @GetMapping("/ex07")
    public void ex07(){
        MemberAddress address = MemberAddress.builder()
                .addr1("주소1")
                .addr2("주소2")
                .zipCode("10000")
                .build();

        address = memberAddressRepository.saveAndFlush(address);

        Member member = Member.builder()
                .userId("user02")
                .userPw("123456")
                .userNm("사용자02")
                .address(address)
                .email("user02@user.org")
                .mobile("01000000000")
                .build();

        memberRepository.save(member);
    }


    @GetMapping("/ex08")
    public void ex08(){
        Member member = memberRepository.findById(2L).orElse(null);
        MemberAddress address = member.getAddress();

        log.info(address.toString());
    }

    @GetMapping("/ex09")
    public void ex09(){
        MemberAddress address = memberAddressRepository.findById(1L).orElse(null);
        Member member = address.getMember();
        //지연로딩, 로그를 보면 각각 표시됨
        log.info(member.toString());
    }

    @GetMapping("/ex10")
    public void ex10(){
        Member member = memberRepository.findById(1L).orElse(null);
        memberRepository.delete(member);
        //cascade로 묶여있기 때문에, BoardData부터 삭제 작업을 진행하고,
        // 그 다음 member를 삭제하게 된다. (외래키 있을 시 묶여있는 관계부터 삭제해야하기 때문)
        memberRepository.flush();
    }


}
