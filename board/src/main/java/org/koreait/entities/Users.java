package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.constants.UserType;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(indexes = {
        @Index(name="idx_userNm", columnList="userNm"),
        @Index(name = "idx_regDt_desc", columnList = "regDt DESC")

})
public class Users extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userNo;
    @Column(length = 40, unique = true, nullable = false)
    private String userId;
    @Column(length = 65, nullable = false)
    private String userPw;
    @Column(length = 45, nullable = false)
    private String userNm;
    @Column(length = 100, nullable = false)
    private String email;
    @Column(length = 11)
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private UserType type = UserType.USER;


}
