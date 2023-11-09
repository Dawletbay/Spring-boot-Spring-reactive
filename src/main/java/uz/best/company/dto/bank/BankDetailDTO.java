package uz.best.company.dto.bank;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class BankDetailDTO implements Serializable {
    UUID id;

    String name;

    String accountNumber;

    String mfo;

    String oked;
}
