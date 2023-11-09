package com.best.company.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateUtils {

    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
    public static final DateTimeFormatter yearLastTwoDigitFormat = DateTimeFormatter.ofPattern("yy");
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
    public static final DateTimeFormatter dateTimeHyphenFormatter = DateTimeFormatter.ofPattern(LONG_DATE_FORMAT);
    public static final DateTimeFormatter dateTimeHyphenWithoutSecondFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter dayMonthYearDotFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final DateTimeFormatter yearMonthDayHyphenFormatter = DateTimeFormatter.ofPattern(SHORT_DATE_FORMAT);
    public static final DateTimeFormatter yearMonthDayTimezoneFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static String formatYearLastTwoDigit(LocalDate date) {
        return yearLastTwoDigitFormat.format(date);
    }
}
