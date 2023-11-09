package uz.best.company.dto;

import lombok.Getter;
import lombok.Setter;
import uz.best.company.enums.CommonStatus;

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
