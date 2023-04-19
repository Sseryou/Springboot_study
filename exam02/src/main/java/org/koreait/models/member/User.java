package org.koreait.models.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data @Builder
public class User {

    private Long userNo;
    private String userId;
    //@JsonIgnore
    private String userPw;
    private String userNm;

    /**
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime regDt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modDt;
    */


}
