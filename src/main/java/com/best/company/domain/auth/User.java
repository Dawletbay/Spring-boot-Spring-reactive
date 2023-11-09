package com.best.company.domain.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import com.best.company.domain.base.AuditEntity;
import com.best.company.dto.auth.UserDTO;
import com.best.company.enums.CommonStatus;
import com.best.company.enums.LanguageEnum;

import java.util.UUID;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 18.07.2023 17:30
 */
@Table("users")
@Getter
@Setter
public class User extends AuditEntity {

    static final long serialVersionUID = 438473L;

    @Column("certificate_name")
    private String certificateName;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("patronymic")
    private String patronymic;

    @Column("username")
    private String username;

    @Column("password")
    private String password;

    @Column("phone")
    private String phone;

    @Column("language")
    private LanguageEnum language = LanguageEnum.ru;

    @Column("status")
    private CommonStatus status = CommonStatus.PENDING;

    @Column("activation_key")
    private String activationKey;

    @Column("secret_key")
    private String secretKey;

    @Column("company_id")
    private UUID companyId;

    @Column("address_id")
    private UUID addressId;


    public UserDTO getDto() {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        return id != null && id.equals(((User) o).id);
    }
}
