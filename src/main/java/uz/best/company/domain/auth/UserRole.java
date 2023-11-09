package uz.best.company.domain.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import uz.best.company.domain.base.SimpleEntity;

import java.util.UUID;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 18.07.2023 17:30
 */
@Table("user_role")
@Getter
@Setter
public class UserRole extends SimpleEntity {

    static final long serialVersionUID = 34354438473L;

    @Column("role_id")
    private String roleId;

    @Column("user_id")
    private UUID userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserRole)) {
            return false;
        }
        return id != null && id.equals(((UserRole) o).id);
    }
}
