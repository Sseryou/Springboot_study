package org.koreait.entities;

import jakarta.persistence.*;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.koreait.constants.MemberType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="users", indexes={
        @Index(name="idx_userNm", columnList = "userNm"),
        @Index(name="idx_regDt_desc", columnList = "regDt DESC")
})
//테이블 이름이 users로 만들어진다.
//테이블 이름을 따로 설정 하지 않으면 클래스 이름으로 생성된다.
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long userNo;
    //기본 전략은 auto이고, sequence, identity는 서로 호환이 되지 않는다.(한쪽은 oracle, 한쪽은 mysql)
    //auto로 하면 둘 다 호환이 된다.

    @Column(length = 45, unique = true, nullable = false)
    private String userId;
    //varchar2(45), unique, not null

    @Column(length = 65, nullable = false)
    private String userPw;

    @Column(length = 40, nullable = false)
    private String userNm;

    @Column(name="cellPhone", length = 11)
    private String mobile;
    //@Transient : 엔티티 상태감지 하지않음
    //이름이 moblie이 아닌 cellPhone으로 컬럼이 만들어진다.

    @Enumerated(EnumType.STRING)
    @Column(length = 40)
    private MemberType type;
    //Enum 타입은 항상 String으로 바꿔서!!
    //기본값이 ordinal이라 해도 쓰면 안된다...
    //ordinal = 상수의 '위치번호' 0,1,2....
    //위치는 바뀌거나 소멸할 수 있으므로 위험하다.

    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private LocalDate regDt;
    //알아서 현재 날짜 주입
    //@Temporal(TemporalType.DATE) : DB에 주입시 날짜만 들어가게 됨.

    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    private LocalDate modDt;
    //업데이트 시 날짜 주입

}
