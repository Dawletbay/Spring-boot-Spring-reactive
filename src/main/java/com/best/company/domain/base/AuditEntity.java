package com.best.company.domain.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 18.07.2023 17:30
 */

@Getter
@Setter
public abstract class AuditEntity extends BaseEntity {

    static final long serialVersionUID = 23853L;

    @CreatedDate
    @Column("created_date")
    protected LocalDateTime createdDate = LocalDateTime.now();

    @CreatedDate
    @Column("updated_date")
    protected LocalDateTime updatedDate = LocalDateTime.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuditEntity)) {
            return false;
        }
        return id != null && id.equals(((AuditEntity) o).id);
    }
}
