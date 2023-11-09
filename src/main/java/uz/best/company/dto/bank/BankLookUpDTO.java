package uz.best.company.dto.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 21.08.2023 15:47
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankLookUpDTO implements Serializable {

    UUID id;

    String name;

    String mfo;
}
