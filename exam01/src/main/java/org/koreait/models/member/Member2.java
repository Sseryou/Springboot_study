package org.koreait.models.member;

import lombok.Builder;
import lombok.ToString;

@Builder @ToString
public class Member2 {

    private Long userNo;
    private String userId;
    private String userPw;
    private String userNm;

}
