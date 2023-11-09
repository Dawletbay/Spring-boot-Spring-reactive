package uz.best.company.dto.address;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 16.11.2021 15:07
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class AddressDTO implements Serializable {

    UUID regionId;

    UUID districtId;

    String street;

    String house;

    String apartment;

    String longitude;

    String latitude;
}
