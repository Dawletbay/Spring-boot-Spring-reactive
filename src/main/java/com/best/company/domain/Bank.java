package com.best.company.domain;

import com.best.company.domain.base.LocalizedEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import com.best.company.dto.bank.BankDTO;

import java.util.UUID;

@Getter
@Setter
@Table("bank")
public class Bank extends LocalizedEntity {

    @Column("code")
    private String code;

    @Column("mfo")
    private String mfo;

    @Column("tin")
    private String tin;

    @Column("parent_id")
    private UUID parentId;


    public BankDTO toDTO() {
        BankDTO bankDTO = new BankDTO();
        BeanUtils.copyProperties(this, bankDTO);
        bankDTO.setName(this.getNameEn());
        return bankDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bank)) {
            return false;
        }
        return id != null && id.equals(((Bank) o).id);
    }
}
