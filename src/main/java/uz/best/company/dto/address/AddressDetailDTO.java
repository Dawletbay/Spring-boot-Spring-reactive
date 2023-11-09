package uz.best.company.dto.address;

import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.best.company.dto.base.CommonDTO;

import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 20.07.2023 15:31
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class AddressDetailDTO implements Serializable {

   CommonDTO region;

   CommonDTO district;

   String street;

   String house;

   String apartment;

   String longitude;

   String latitude;
}
