package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @Builder //JPA는 기본 생성자가 필요하다. lombok의 Builder는 기본 생성자를 만들지 않는다.....
@NoArgsConstructor @AllArgsConstructor  //Builder를 어떻게든 기본생성자로 쓰기 위해서 이 2개를 추가한다.
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

    //현재 부모가 회원, 자식이 보드
    //mappedBy로 주인이 매핑할수 있게 설정
    //cascade를 이용하여 자식이 삭제되면 부모도 삭제되게 설정(엔티티 내부)
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE) //fetch = FetchType.EAGER : 즉시 조인, 잘 안씀.
    private List<BoardData> boardData = new ArrayList<>();
    //한명의 회원이 여러 게시글을 갖고 있을 수 있으므로 List 사용

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId")
    private MemberAddress address;

}
