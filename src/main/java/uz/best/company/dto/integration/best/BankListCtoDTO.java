package uz.best.company.dto.integration.best;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 01.08.2023 17:47
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class BankListCtoDTO implements Serializable {
    static final long serialVersionUID = 1L;

    Long id;

    String name;

    String code; //MFO

    String tin;

    String parentCode;

    String categoryCode; //Code

    Long regionCode;

    Long districtCode;

    String street;

    String house;

    String apartment;
}
