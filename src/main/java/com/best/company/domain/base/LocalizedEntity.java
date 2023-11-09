package com.best.company.domain.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 29/10/2021 19:47
 */

@Getter
@Setter
public class LocalizedEntity extends BaseEntity implements Serializable {

    static final long serialVersionUID = 348239438L;

    @Column("name_uz")
    protected String nameUz;

    @Column("name_en")
    protected String nameEn;

    @Column("name_ru")
    protected String nameRu;
}
