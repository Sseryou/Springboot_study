package org.koreait.repositories;

import org.koreait.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> { //엔티티 클래스, 기본 키의 자료형
    //추상 메서드 정의, 패턴
    Member findByUserId(String userId);

}
