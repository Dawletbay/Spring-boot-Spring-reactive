package uz.best.company.domain.address;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import uz.best.company.domain.base.SimpleEntity;

import java.util.UUID;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 20.07.2023 14:47
 */
@Table("address")
@Getter
@Setter
public class Address extends SimpleEntity {

    static final long serialVersionUID = 87766546L;

    @Column("street")
    private String street;

    @Column("house")
    private String house;

    @Column("apartment")
    private String apartment;

    @Column("longitude")
    private String longitude;

    @Column("latitude")
    private String latitude;

    @Column("region_id")
    private UUID regionId;

    @Column("district_id")
    private UUID districtId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }
        return id != null && id.equals(((Address) o).id);
    }
}
