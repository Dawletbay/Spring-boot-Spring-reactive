package com.best.company.dto.base;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.UUID;

import static lombok.AccessLevel.PROTECTED;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 20.07.2023 15:31
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@FieldDefaults(level = PROTECTED)
public class CommonDTO implements Serializable {

    UUID id;

    String name;

    public CommonDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
