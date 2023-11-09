package com.best.company.domain.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import com.best.company.enums.Roles;

import java.io.Serializable;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 18.07.2023 17:30
 */

@Getter
@Setter
@Table("roles")
public class Role implements Serializable {

    static final long serialVersionUID = 859485298L;

    @Id
    @Column("name")
    private Roles name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        return name != null && name.equals(((Role) o).name);
    }
}
