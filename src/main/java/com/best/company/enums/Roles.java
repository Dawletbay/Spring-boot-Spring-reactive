package com.best.company.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

@Getter
public enum Roles implements Serializable {

    SUPER_ADMIN("ROLE_SUPER_ADMIN") {
        @Override
        public String getName(LanguageEnum language) {
            return switch (language) {
                case uz -> "Super Admin";
                case en -> "Super Admin";
                default -> "Супер администратор";
            };
        }
    },
    ADMIN("ROLE_ADMIN") {
        @Override
        public String getName(LanguageEnum language) {
            return switch (language) {
                case uz -> "Admin";
                case en -> "Admin";
                default -> "Администратор";
            };
        }
    };

    private final String name;
    private final String code;

    Roles(String code) {
        this.code = code;
        this.name = this.getName();
    }

    public abstract String getName(LanguageEnum language);

//    public String getName() {
//        return getName(SecurityUtils.getCurrentRequestLanguageEnum());
//    }

    public static Roles getByCode(String code) {
        for (Roles roles : values()) {
            if (roles.getCode().equals(code)) {
                return roles;
            }
        }
        return null;
    }

    @JsonCreator
    @SuppressWarnings("unused")
    public static Roles forCodeValue(Map<String, String> value) {
        String roleCode = value.get("code");
        for (Roles roles : values()) {
            if (roles.getCode().equals(roleCode) || roles.name().equals(roleCode)) {
                return roles;
            }
        }
        return null;
    }
}
