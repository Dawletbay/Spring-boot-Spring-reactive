package uz.best.company.dto.integration.best;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 01.08.2023 17:47
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class LoginDTO implements Serializable {
    static final long serialVersionUID = 1L;

    String username;

    String password;

    boolean rememberMe;
}
