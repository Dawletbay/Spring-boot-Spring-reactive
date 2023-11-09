package uz.best.company.domain.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.UUID;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 18.07.2023 17:30
 */

@Getter
@Setter
public abstract class SimpleEntity implements Serializable {

    static final long serialVersionUID = 344322L;

    @Id
    protected UUID id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimpleEntity)) {
            return false;
        }
        return id != null && id.equals(((SimpleEntity) o).id);
    }


    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
