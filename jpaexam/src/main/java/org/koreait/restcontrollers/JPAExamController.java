package org.koreait.restcontrollers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.BoardData;
import org.koreait.entities.Member;
import org.koreait.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jpa")
@Transactional
//@RequiredArgsConstructor
public class JPAExamController {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private EntityManager em;

    @GetMapping("/ex01")
    public void ex01(){

        Member member = new Member();
        member.setUserId("user01");
        member.setUserPw("123456");
        member.setUserNm("사용자01");

        em.persist(member);
        em.flush();

        for(int i = 1; i <= 10; i++) {
            BoardData boardData = new BoardData();
            boardData.setSubject("제목1"+i);
            boardData.setContent("내용1"+i);
            boardData.setMember(member);

            em.persist(boardData);
            em.flush();
        }

    }
    @GetMapping("/ex02")
    public void ex02(){
        Member member = em.find(Member.class, 1L);
        List<BoardData> list = member.getBoardDatas(); //회원이 작성한 게시글 목록
        list.stream().forEach(System.out::println);
    }

    @GetMapping("/ex03")
    public void ex03(){
        BoardData boardData = em.find(BoardData.class, 1L);
        System.out.println(boardData);

        Member member = boardData.getMember();
        String userId = member.getUserId();
        System.out.println(userId);
    }

    @GetMapping("/ex04")
    public void ex04(){
        //중요. join fetch
        String sql = "SELECT b FROM BoardData b JOIN FETCH b.member WHERE b.id=:id";
        //현재 LAZY, 필요에 따라 즉시 조인하기 위해서 FETCH를 사용한다.
        //지연 로딩은 한번 캐싱을 하게 되지만, FETCH를 이용해 즉시 DB에 접속하여 불필요한 캐싱을 하지 않게 한다.
        //(캐시 과정 생략)

        TypedQuery<BoardData> tq = em.createQuery(sql, BoardData.class);
        tq.setParameter("id", 1L);
        BoardData boardData = tq.getSingleResult();
        System.out.println(boardData);
    }

    @GetMapping("/ex05")
    public void ex05() {
        //repository.findByUserNmContaining()
        // flush(), 조회 findAll, findOne -> 실행 전 flush 자동으로 먼저 진행
        List<Member> members = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Member member = new Member();
            member.setUserId("user" + i);
            member.setUserPw("123456");
            member.setUserNm("사용자" + i);
            members.add(member);
        }

        repository.saveAllAndFlush(members);
    }

    @GetMapping("/ex06")
    public void ex06() {
        //Pageable pageable = PageRequest.of(0, 3);
        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Order.desc("regDt")));
        List<Member> members = repository.findByUserNmContaining("자", pageable);

        members.stream().forEach(System.out::println);
    }

    @GetMapping("/ex07")
    public void ex07() {
        List<Member> members = repository.findMembers("자");
        members.forEach(System.out::println);
    }


}
