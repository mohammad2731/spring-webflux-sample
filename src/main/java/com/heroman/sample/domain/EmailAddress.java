package com.heroman.sample.domain;

import com.heroman.sample.core.exception.InvalidEmailAddressException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

public final class EmailAddress {
    public static final EmailAddress EMPTY_EMAIL_ADDRESS = new EmailAddress(null);
    private final String value;

    private EmailAddress(String value) {
        this.value = value;
    }

    public static EmailAddress create(String value) {
        if (StringUtils.isBlank(value)) {
            return EMPTY_EMAIL_ADDRESS;
        }
        if (!EmailValidator.getInstance().isValid(value)) {
            throw new InvalidEmailAddressException();
        }
        return new EmailAddress(value);
    }

    public String getValue() {
        return value;
    }
}
