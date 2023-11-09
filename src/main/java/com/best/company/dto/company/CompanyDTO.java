package com.best.company.dto.company;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 20.07.2023 17:30
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CompanyDTO implements Serializable {
    UUID id;
    String name;
    String tin;
    String brand;
    String phone;
    String director;
    String accountant;
    UUID addressId;
    UUID businessTypeId;
}
