package org.koreait.tests;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class JPAExam01Test {

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("엔티티 테이블 생성 테스트")
    void test01(){
        Member member = new Member();
        member.setUserNo(1l);
        member.setUserId("user01");
        member.setUserPw("123456");
        member.setUserNm("사용자01");

        em.persist(member); //상태 변화 감지
        em.flush(); // DB에 상태변경 사항 반영

        em.detach(member); //상태 변화 감지 x

        member.setUserNm("(수정)사용자01"); //상태가 변경
        em.flush(); //UPDATE 쿼리

        em.merge(member); //분리 상태 -> 영속성 관리 상태(상태 변경 감지)

        Member member2 = em.find(Member.class, member.getUserNo());
        System.out.println(member2);

       // em.remove(member);
       // em.flush(); //DELETE 쿼리

        String sql = "SELECT m FROM Member m WHERE userNo = :userNo";
        TypedQuery<Member> typedQuery = em.createQuery(sql, Member.class);
        typedQuery.setParameter("userNo", member.getUserNo());

        List<Member> members = typedQuery.getResultList();
        members.get(0).setUserNm("(수정2)사용자01");

        em.flush();

    }

}
