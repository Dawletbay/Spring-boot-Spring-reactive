package com.best.company.dto.auth;

import com.best.company.enums.CommonStatus;
import com.best.company.enums.LanguageEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UserDTO implements Serializable {

    UUID id;

    String certificateName;

    String firstName;

    String lastName;

    String patronymic;

    String username;

    String password;

    String phone;

    LanguageEnum language;

    CommonStatus status;

    String activationKey;

    String secretKey;

    UUID companyId;

    UUID addressId;

}
