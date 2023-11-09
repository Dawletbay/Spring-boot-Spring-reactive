package uz.best.company.domain.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 18.07.2023 17:30
 */

@Getter
@Setter
public abstract class BaseEntity extends SimpleEntity {

    static final long serialVersionUID = 293012L;

    @Column("deleted")
    protected boolean deleted = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseEntity)) {
            return false;
        }
        return id != null && id.equals(((BaseEntity) o).id);
    }
}
