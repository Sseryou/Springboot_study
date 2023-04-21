package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity @Builder
@NoArgsConstructor @AllArgsConstructor
public class BoardData extends BaseEntity{
    @Id @GeneratedValue//자동적으로 증가하는 값
    private Long Id; //게시글 번호
    @Column(nullable = false)
    private String subject; //제목
    @Lob //large object
    @Column(nullable = false)
    private String content; //내용


    //외래키가 있으면 주인. 외래키를 바꿀 수 있으면 주인이다.
    @ToString.Exclude //서로의 순환참조를 막는다. (ToString 재정의에서 제외)
    // 현재 member -> BoardData -> member...순으로 순환참조 발생(lombok toString 문제)
    @ManyToOne(fetch = FetchType.LAZY) //지연 로딩. 필요할 때만 join을 하게 된다.
    @JoinColumn(name = "userNo")
    private Member member; //회원 한명이 여러 게시글 사용
    //id_userNo...로 자동으로 만들어지니 명시해줄 필요가 있음




}
