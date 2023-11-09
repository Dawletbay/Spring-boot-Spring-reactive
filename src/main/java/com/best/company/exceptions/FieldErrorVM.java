package com.best.company.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 26.07.2023 13:05
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldErrorVM implements Serializable {

    private String objectName;

    private String field;

    private String message;

}
