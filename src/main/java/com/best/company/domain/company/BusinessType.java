package com.best.company.domain.company;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import com.best.company.domain.base.LocalizedEntity;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 24.07.2023 17:30
 */

@Getter
@Setter
@Table("business_type")
public class BusinessType extends LocalizedEntity {

    static final long serialVersionUID = 89838L;

    @Column("code")
    private String code;

}
