package com.best.company.dto.bank;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class BankDTO implements Serializable {
    String name;
    String mfo;
    String code;
    String tin;
    UUID parentId;
}
