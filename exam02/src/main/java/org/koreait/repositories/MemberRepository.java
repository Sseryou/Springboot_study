package org.koreait.repositories;

import com.querydsl.core.BooleanBuilder;
import org.koreait.entities.Member;
import org.koreait.entities.QMember;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor { //엔티티 클래스, 기본 키의 자료형
    //추상 메서드 정의, 패턴
    Member findByUserId(String userId);

    List<Member> findByUserIdNot(String userId); // userId <> ...
    List<Member> findByUserNmContaining(String key); // userNm LIKE '%:key'

    //import org.springframework.data.domain.Pageable;
    List<Member> findByRegDtBetween(LocalDate sDate, LocalDate eDate, Pageable pageable);

    List<Member> findByRegDtBetweenOrderByRegDtDesc(LocalDate sDate, LocalDate eDate);

    //테이블명이 아님. 엔티티명과 일치해야 한다.
    //%:key%가 param의 key로 들어가고, key는 다시 String의 key로 들어간다.
    @Query("SELECT m FROM Member m WHERE m.userNm LIKE %:key% ORDER BY m.regDt DESC")
    List<Member> findByUsers(@Param("key") String keyword);
    //이 방식도 편하긴 하지만 문제가 있다. 오류 검출을 실행단계에서만 할 수 있다.
    //그래서 쿼리빌더를 사용하게 된다.

    //default는 인터페이스 내에서 완전히 구현된 메서드를 작성할 수 있게 해준다.
    default List<Member> findUsers(String keyword){
        QMember member = QMember.member;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(member.userNm.contains(keyword))
                .and(member.userId.notIn("user1", "user2"));

        //반환값을 보고, 문서에서 찾아서 활용한다.
        List<Member> members = (List)this.findAll(builder);
        return members;
    }



}
