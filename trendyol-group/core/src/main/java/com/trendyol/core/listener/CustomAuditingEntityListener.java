package com.trendyol.core.listener;

import com.trendyol.core.entity.BaseEntity;
import jakarta.persistence.PreRemove;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


public class CustomAuditingEntityListener extends AuditingEntityListener {
    @PreRemove
    public void preRemove(Object entity) {
        if (entity instanceof BaseEntity baseEntity) {
            baseEntity.setDeleted(true);
            baseEntity.setEnable(false);
        }
    }
}
