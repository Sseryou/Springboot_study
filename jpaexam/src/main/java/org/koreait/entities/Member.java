package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Data
public class Member extends BaseEntity{

    @Id @GeneratedValue
    private Long userNo;

    @Column(length = 40, unique = true, nullable = false)
    private String userId;
    @Column(length = 65, nullable = false)
    private String userPw;
    @Column(length = 40, nullable = false)
    private String userNm;

    //조인을 많이 하게 되는 경우, 평상시에 LAZY로 두고 필요할 때만 패치조인을 통해 즉시 로딩하는 것을 권장한다.
    //또는 쿼리를 두번 나눠 부르는게 낫다...
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY) //반드시 연관관계의 주인을 명시해야 한다. 관계를 좌지우지 할수 있는 테이블이 주인
    //many쪽이 주인
    private List<BoardData> boardDatas = new ArrayList<>();



    /**
    @ManyToOne
    @JoinColumn(name = "managerNo") //셀프조인..매니저도 회원인 경우..
    private Member manager;
    */

    @OneToOne
    @JoinColumn(name = "addressId")
    @ToString.Exclude
    private Address address;

}
