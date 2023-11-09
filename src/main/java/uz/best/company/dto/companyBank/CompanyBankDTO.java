package uz.best.company.dto.companyBank;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@FieldDefaults(level = PRIVATE)
public class CompanyBankDTO implements Serializable {

    String accountNumber;

    String oked;

    boolean main;

    UUID companyId;

    UUID bankId;
}
