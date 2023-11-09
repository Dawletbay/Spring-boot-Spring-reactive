package uz.best.company.dto.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.UUID;

import static lombok.AccessLevel.PROTECTED;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 20.07.2023 15:31
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = PROTECTED)
public class CommonCodeDTO extends CommonDTO implements Serializable {

    String code;

    public CommonCodeDTO(UUID id, String name, String code) {
        super(id, name);
        this.code = code;
    }
}
