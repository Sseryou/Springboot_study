package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter //추상 클래스 이기 때문에 toString이 필요 없음, 꼭 필요한 getter와 setter만 넣음
@MappedSuperclass //공통 엔티티를 상속할 때 사용해야 사용하는 쪽에서 상속받을 수 있다.
@EntityListeners(AuditingEntityListener.class) //엔티티의 상태변화 감지. 활성화 필요
public abstract class BaseEntity {
    //공통 속성화 - 추상 클래스로 만든다.
    //이건 엔티티가 아님. 객체를 담아두는 용도
    //공통적인 것만 쓰기 위해서 객체를 만듬
    //사용할 엔티티에 상속시키면 된다.



    //jakarta에 있는걸로 사용
    @CreatedDate //영속성에 추가 될 때만 생성
    @Column(updatable = false) //수정(UPDATE) 불가
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime regDt; //등록일자

    @LastModifiedDate //영속성 안에서 수정할 때만 생성
    @Column(insertable = false) //추가(INSERT) 불가, 수정 할때만 변경
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modDt; // 수정일자

}
