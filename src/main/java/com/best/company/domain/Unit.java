package com.best.company.domain;

import com.best.company.domain.base.LocalizedEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("unit")
public class Unit extends LocalizedEntity {

    @Column("measure_id")
    private Long measureId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Unit)) {
            return false;
        }
        return id != null && id.equals(((Unit) o).id);
    }
}
