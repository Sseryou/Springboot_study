package org.koreait.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseUserEntity extends BaseEntity{
    @CreatedBy //최초 추가한 아이디
    @Column(length = 40, updatable = false)
    private String createdBy;

    @LastModifiedBy //수정한 아이디
    @Column(length = 40, insertable = false)
    private String modifiedBy;


}
