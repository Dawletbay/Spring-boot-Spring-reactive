package uz.best.company.domain.company;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import uz.best.company.domain.base.AuditEntity;
import uz.best.company.dto.company.CompanyDTO;
import uz.best.company.dto.company.CompanyListDTO;

import java.util.UUID;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 18.07.2023 17:30
 */

@Getter
@Setter
@Table("company")
public class Company extends AuditEntity {

    static final long serialVersionUID = 8498298L;

    @Column("tin")
    private String tin;

    @Column("name")
    private String name;

    @Column("brand")
    private String brand;

    @Column("phone")
    private String phone;

    @Column("director")
    private String director;

    @Column("accountant")
    private String accountant;

    @Column("address_id")
    private UUID addressId;

    @Column("business_type_id")
    private UUID businessTypeId;

    @Column("activity_type_id")
    private UUID activityTypeId;

    public CompanyDTO getDetailDTO() {
        CompanyDTO dto = new CompanyDTO();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

    public CompanyListDTO toListDTO() {
        CompanyListDTO companyListDTO = new CompanyListDTO();
        BeanUtils.copyProperties(this, companyListDTO);
        return companyListDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Company)) {
            return false;
        }
        return id != null && id.equals(((Company) o).id);
    }
}
