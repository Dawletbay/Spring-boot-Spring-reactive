package uz.best.company.dto.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import uz.best.company.dto.base.CommonCodeDTO;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class RegionDTO extends CommonCodeDTO {

    String mapValue;

    public RegionDTO(UUID id, String name, String code, String mapValue) {
        super(id, name, code);
        this.mapValue = mapValue;
    }
}
