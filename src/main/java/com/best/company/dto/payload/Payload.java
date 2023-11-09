package com.best.company.dto.payload;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Payload implements Serializable {
    private UUID id;
}
