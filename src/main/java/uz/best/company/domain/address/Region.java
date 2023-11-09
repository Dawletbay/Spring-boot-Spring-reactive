package uz.best.company.domain.address;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import uz.best.company.domain.base.LocalizedEntity;

import java.io.Serializable;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 20.07.2023 14:47
 */
@Table("region")
@Getter
@Setter
public class Region extends LocalizedEntity implements Serializable {

    static final long serialVersionUID = 489827343L;

    @Column("code")
    private Long code;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Region)) {
            return false;
        }
        return id != null && id.equals(((Region) o).id);
    }
}
