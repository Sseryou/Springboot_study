package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class BoardData extends BaseEntity{
    @Id @GeneratedValue//자동적으로 증가하는 값
    private Long Id; //게시글 번호
    @Column(nullable = false)
    private String subject; //제목
    @Lob //large object
    @Column(nullable = false)
    private String content; //내용


    @ManyToOne
    @JoinColumn(name = "userNo")
    private Member member; //회원 한명이 여러 게시글 사용
    //id_userNo...로 자동으로 만들어지니 명시해줄 필요가 있음



}
