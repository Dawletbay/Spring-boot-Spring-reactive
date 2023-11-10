package com.best.company.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

import static lombok.AccessLevel.PRIVATE;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 26.10.2021 14:47
 */
@Service
@Transactional(readOnly = true)
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class LocalizationService {

    MessageSource messageSource;

    public String localize(String code) {
        return this.localize(code, null, code, Locale.getDefault());
    }

    public String localize(String code, Object[] params) {
        return this.localize(code, params, Locale.getDefault());
    }

    public String localize(String code, String defaultMessage) {
        return messageSource.getMessage(code, null, defaultMessage, Locale.getDefault());
    }

    public String localize(String code, Object[] params, Locale locale) {
        return this.localize(code, params, code, locale);
    }

    public String localize(String code, Object[] params, String defaultMessage, Locale locale) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        return messageSource.getMessage(code, params, defaultMessage, locale);
    }
}
