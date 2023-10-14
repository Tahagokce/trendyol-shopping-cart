package com.trendyol.core.entity;

import com.trendyol.core.listener.CustomAuditingEntityListener;
import com.trendyol.core.util.SecurityUtil;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
@EntityListeners({CustomAuditingEntityListener.class})
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @CreatedDate
    @Column(nullable = false)
    protected Date createdDate;

    @LastModifiedDate
    protected Date lastModifiedDate;

    @Column(nullable = false)
    private Long createdBy;

    private Long lastModifiedBy;

    private boolean enable = true;

    private boolean isDeleted = false;

    private String transactionId;

    @PrePersist
    public void onCreate() {
        Date now = new Date();
        lastModifiedDate = now;
        createdDate = now;

        this.createdBy = SecurityUtil.getLoggedUserId().orElse(0L);

        this.transactionId = SecurityUtil.getLoggedTransactionId();
    }

    @PreUpdate
    public void onUpdate() {
        lastModifiedDate = new Date();

        lastModifiedBy = SecurityUtil.getLoggedUserId().orElse(0L);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                '}';
    }
}
