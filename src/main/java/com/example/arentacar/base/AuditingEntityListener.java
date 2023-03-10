package com.example.arentacar.base;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.ZonedDateTime;

@Component
public class AuditingEntityListener {
    @PrePersist
    @PreUpdate
    public void setLastModifiedDate(AbstractEntity entity) {
        if (ObjectUtils.isEmpty(entity.getCreatedDate())) {
            entity.setCreatedDate(ZonedDateTime.now());
        }
        entity.setLastModifiedDate(ZonedDateTime.now());

        if (StringUtils.isEmpty(entity.getCreatedBy())) {
            entity.setCreatedBy("System");
        }
    }
}