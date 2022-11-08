package com.example.demo.global.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @CreatedDate
    @Column(name = "created_datetime", columnDefinition = "datetime default CURRENT_TIMESTAMP comment '생성 일시'")
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    @Column(name = "modified_datetime", columnDefinition = "datetime default CURRENT_TIMESTAMP comment '수정 일시'")
    private LocalDateTime modifiedDateTime;

}
