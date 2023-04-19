package org.koreait.models.member;

public class Member {
    private Long userNo;
    private String userId;
    private String userPw;
    private String userNm;

    private Member() {}

    @Override
    public String toString() {
        return "Member{" +
                "userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userNm='" + userNm + '\'' +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    //setter를 쓰지 않고 하는 방식
    protected static class Builder {
        private Member member = new Member();

        public Builder userNo(Long userNo){
            member.userNo = userNo;

            return this;
        }

        public Builder userId(String userId){
            member.userId = userId;
            return this;
        }
        public Builder userPw(String userPw){
            member.userPw = userPw;
            return this;
        }
        public Builder userNm(String userNm){
            member.userNm = userNm;
            return this;
        }
        public Member build(){
            return member;
        }
    }

}
