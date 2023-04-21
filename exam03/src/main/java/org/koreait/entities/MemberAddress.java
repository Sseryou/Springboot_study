package org.koreait.entities;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class MemberAddress extends BaseEntity{

    //member와 1:1 매핑 연습
    @Id @GeneratedValue
    private Long Id;
    @Column(length = 10, nullable = false)
    private String zipCode; //우편번호
    @Column(length = 100, nullable = false)
    private String addr1; //주소
    @Column(length = 100)
    private String addr2; //나머지 주소

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    Member member;

}
