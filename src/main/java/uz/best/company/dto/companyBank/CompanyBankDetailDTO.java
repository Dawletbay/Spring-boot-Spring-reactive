package uz.best.company.dto.companyBank;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import uz.best.company.dto.bank.BankLookUpDTO;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyBankDetailDTO implements Serializable {
    UUID id;

    BankLookUpDTO bank;

    BankLookUpDTO parentBank;

    String accountNumber;

    String oked;

    Boolean main;
}
