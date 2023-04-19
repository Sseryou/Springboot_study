package org.koreait.models.member;

public class Ex01 {

    public static void main(String[] args){
        Member member = Member.builder()
                .userNo(1L)
                .userId("user01")
                .userPw("123456")
                .userNm("사용자01")
                .build();

        System.out.println(member);

        Member2 member2 = Member2.builder()
                .userNo(2L)
                .userId("user02")
                .userPw("123456")
                .userNm("사용자02")
                .build();
        System.out.println(member2);

        //Member2 member3 = new Member2();

    }

}
