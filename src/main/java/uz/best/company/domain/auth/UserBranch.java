package uz.best.company.domain.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import uz.best.company.domain.base.SimpleEntity;

import java.util.UUID;


@Getter
@Setter
@Table("user_branch")
public class UserBranch extends SimpleEntity {

    @Column("user_id")
    private UUID userId;

    @Column("branch_id")
    private UUID branchId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserBranch)) {
            return false;
        }
        return id != null && id.equals(((UserBranch) o).id);
    }
}
