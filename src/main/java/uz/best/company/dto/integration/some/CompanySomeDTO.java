package uz.best.company.dto.integration.some;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 02.08.2023 15:31
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanySomeDTO implements Serializable {

    Long code1;
    Long code2;
    String tin;
    String name;
    String shortName;
    String fullName;
    String mfo;
    String account;
    String oked;
    String director;
    String branchName;
    String address;
    String mobile;

    public String getName() {
        StringBuilder name = new StringBuilder();
        if (this.name != null) {
            name.append(this.name);
        } else if (this.shortName != null) {
            name.append(this.shortName);
        } else if (this.fullName != null) {
            name.append(this.fullName);
        }
        return name.toString();
    }
}
