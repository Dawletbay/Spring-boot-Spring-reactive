package uz.best.company.dto.company;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import uz.best.company.dto.address.AddressDetailDTO;
import uz.best.company.dto.bank.BankDetailDTO;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 21.08.2023 10:41
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CompanyDetailDTO extends CompanyDTO {

    AddressDetailDTO address;

    BankDetailDTO bankDetailDTO;

    Long activeBranches;
}
