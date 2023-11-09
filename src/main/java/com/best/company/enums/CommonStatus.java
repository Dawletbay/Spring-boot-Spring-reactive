package com.best.company.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CommonStatus implements Serializable {
    ACTIVE {
        @Override
        public String getName(LanguageEnum language) {
            return switch (language) {
                case uz -> "Faol";
                case en -> "Active";
                default -> "Актив";
            };
        }
    },

    PENDING {
        @Override
        public String getName(LanguageEnum language) {
            return switch (language) {
                case uz -> "Faollashmagan";
                case en -> "Not activated";
                default -> "В ожидании";
            };
        }
    },

    BLOCKED {
        @Override
        public String getName(LanguageEnum language) {
            return switch (language) {
                case uz -> "Bloklangan";
                case en -> "Blocked";
                default -> "Заблокировано";
            };
        }
    },

    IN_ACTIVE {
        @Override
        public String getName(LanguageEnum language) {
            return switch (language) {
                case uz -> "Faol emas";
                case en -> "No active";
                default -> "Неактивный";
            };
        }
    };

    private final String name;
    private final String code;

    CommonStatus() {
        this.code = this.name();
        this.name = this.getName();
    }

    public abstract String getName(LanguageEnum language);

//    public String getName() {
//        return getName(SecurityUtils.getCurrentRequestLanguageEnum());
//    }

    @JsonCreator
    @SuppressWarnings("unused")
    public static CommonStatus forValue(Map<String, String> value) {
        return CommonStatus.valueOf(value.get("code"));
    }
}
