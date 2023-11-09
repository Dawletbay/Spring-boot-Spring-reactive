package com.best.company.dto;

import com.best.company.enums.CommonStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;


@Getter
@Setter
public class BranchDTO implements Serializable {

    private UUID id;
    private String name;
    private UUID addressId;
    private UUID companyId;
    private CommonStatus status;
}
