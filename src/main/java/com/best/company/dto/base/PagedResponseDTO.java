package com.best.company.dto.base;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 30.07.2021 12:18
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PagedResponseDTO<T> implements Serializable {

    Long totalElements;

    Long totalPages;

    Long numberOfElements;

    Long size;

    Long number;

    List<T> content = new ArrayList<>();
}
