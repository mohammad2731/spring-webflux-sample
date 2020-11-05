package com.heroman.sample.domain;

import com.heroman.sample.core.exception.InvalidEmailAddressException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailAddressTest {

    @Test
    void emptyEmailAddress_shouldHasNullValue() {
        assertThat(EmailAddress.EMPTY_EMAIL_ADDRESS.getValue()).isNull();
    }

    @Test
    void create_whenValueIsNull_shouldReturnEmptyEmailAddress() {
        EmailAddress email = EmailAddress.create(null);
        assertThat(email).isSameAs(EmailAddress.EMPTY_EMAIL_ADDRESS);
    }

    @Test
    void create_whenValueIsEmpty_shouldReturnEmptyEmailAddress() {
        EmailAddress email = EmailAddress.create("");
        assertThat(email).isSameAs(EmailAddress.EMPTY_EMAIL_ADDRESS);
    }

    @Test
    void create_whenValueIsBlank_shouldReturnEmptyEmailAddress() {
        EmailAddress email = EmailAddress.create("  ");
        assertThat(email).isSameAs(EmailAddress.EMPTY_EMAIL_ADDRESS);
    }

    @Test
    void create_whenValueIsNotValid_shouldThrowInvalidEmailAddressException() {
        assertThrows(InvalidEmailAddressException.class, () -> EmailAddress.create("tttttt"));
    }

    @Test
    void create_valueShouldBeSameAsCreateParameter() {
        String value = "user@test.com";
        assertThat(EmailAddress.create(value).getValue()).isEqualTo(value);
    }
}
