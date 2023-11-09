package uz.best.company.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import uz.best.company.domain.base.AuditEntity;
import uz.best.company.enums.CommonStatus;

import java.util.UUID;

@Getter
@Setter
@Table("branch")
public class Branch extends AuditEntity {

    @Column("name")
    private String name;

    @Column("address_id")
    private UUID addressId;

    @Column("company_id")
    private UUID companyId;

    @Column("status")
    private CommonStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Branch)) {
            return false;
        }
        return id != null && id.equals(((Branch) o).id);
    }
}
