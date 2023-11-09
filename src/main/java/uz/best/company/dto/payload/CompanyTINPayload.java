package uz.best.company.dto.payload;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CompanyTINPayload implements Serializable {
    private String tin;
}
