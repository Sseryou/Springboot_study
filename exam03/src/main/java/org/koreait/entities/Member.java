package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Member extends BaseEntity{

    @Id @GeneratedValue
    private Long userNo;
    @Column(length = 45, nullable = false, unique = true)
    private String userId;
    @Column(length = 65, nullable = false)
    private String userPw;
    @Column(length = 45, nullable = false)
    private String userNm;
    @Column(length = 100)
    private String email;
    @Column(length = 11)
    private String mobile;

    //jakarta에 있는거 사용



}
