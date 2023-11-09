package com.best.company.domain.address;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import com.best.company.domain.base.LocalizedEntity;


/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 20.07.2023 14:47
 */
@Table("district")
@Getter
@Setter
public class District extends LocalizedEntity {

    static final long serialVersionUID = 1355321L;

    @Column("code")
    private Long code;

    @Column("district_id")
    private String districtId;

    @Column("region_code")
    private Long regionCode;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof District)) {
            return false;
        }
        return id != null && id.equals(((District) o).id);
    }
}
