package org.koreait.entities;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@Getter @Setter
@MappedSuperclass //상위 클래스로 인식하게 함
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    //객체로 따로 만들지 않고 상속만 해주는 역할이기 때문에 추상화(abstract)

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime regDt;
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private LocalDateTime modDt;

}
