package org.koreait.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    //객체를 만들기 위한 목적의 클래스가 아님.
    //밑 두개는 엔티티의 변화 감지 필요
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(updatable = false) //수정불가
    private LocalDateTime regDt;
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(insertable = false) //추가불가
    private LocalDateTime modDt;


}
