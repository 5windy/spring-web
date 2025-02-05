package com.example.demo.util;

import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Timestamp {

    @CreatedDate
    private java.sql.Timestamp regDate;

    @LastModifiedDate
    private java.sql.Timestamp modDate;
}
