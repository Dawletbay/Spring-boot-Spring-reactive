package com.best.company.domain;

import com.best.company.domain.base.LocalizedEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;


@Table("activity_type")
@Getter
@Setter
public class ActivityType extends LocalizedEntity {

    @Column("code")
    private Long code;

    @Column("parent_id")
    private UUID parentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActivityType)) {
            return false;
        }
        return id != null && id.equals(((ActivityType) o).id);
    }
}
