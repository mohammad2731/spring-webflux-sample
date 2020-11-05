package com.heroman.sample.domain;

import com.heroman.sample.core.exception.InvalidPhoneNumberException;
import org.apache.commons.lang3.StringUtils;

public final class PhoneNumber {
    public static final PhoneNumber EMPTY_PHONE_NUMBER = new PhoneNumber(null);
    private final String value;

    public PhoneNumber(String value) {
        this.value = value;
    }

    public static PhoneNumber create(String value) {
        if (StringUtils.isBlank(value)) {
            return EMPTY_PHONE_NUMBER;
        }
        if (!value.matches("^\\+?\\d+$")) {
            throw new InvalidPhoneNumberException();
        }
        return new PhoneNumber(value);
    }

    public String getValue() {
        return value;
    }
}
