package uz.best.company.domain.company;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import uz.best.company.domain.base.BaseEntity;
import uz.best.company.dto.companyBank.CompanyBankDTO;

import java.util.UUID;

@Getter
@Setter
@Table("company_bank")
public class CompanyBank extends BaseEntity {

    @Column("account_number")
    private String accountNumber;

    @Column("oked")
    private String oked;

    @Column("is_main")
    private boolean main;

    @Column("company_id")
    private UUID companyId;

    @Column("bank_id")
    private UUID bankId;

    public CompanyBankDTO toDTO() {
        CompanyBankDTO companyBankDTO = new CompanyBankDTO();
        BeanUtils.copyProperties(this, companyBankDTO);
        return companyBankDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompanyBank)) {
            return false;
        }
        return id != null && id.equals(((CompanyBank) o).id);
    }
}
