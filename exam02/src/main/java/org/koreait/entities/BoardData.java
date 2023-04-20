package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
public class BoardData {
    @Id @GeneratedValue(strategy = GenerationType.TABLE) //기본키설정, 자동 증가하는 숫자로 설정
    private Long id; //게시글 번호
    @Column(length = 150, nullable = false) //varchar2(150), NOT NULL
    private String subject; //게시글 제목
    @Lob//글자 개많이 넣을수 있음..
    @Column(nullable = false)
    private String content; //게시글 내용
    @Temporal(TemporalType.TIMESTAMP) // 알아서 시간 추가
    @CreationTimestamp //알아서 날짜 추가
    private LocalDateTime regDt; //게시글 작성일자

    @Temporal(TemporalType.TIMESTAMP) // 알아서 시간 추가
    @CreationTimestamp //알아서 날짜 추가
    private LocalDateTime modDt; //게시글 수정일시

    @ManyToOne //회원 쪽에 있는 기본키와 보드에 있는 기본키가 병합되어 외래키 생성
    @JoinColumn(name = "userNo") //userNo를 기준으로 병합되게 변경. board쪽에도 userNo 생성
    private Member member; //회원쪽 기본키를 기준으로 외래키가 생성되게 한다.

}
