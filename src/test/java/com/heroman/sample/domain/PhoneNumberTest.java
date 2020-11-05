package com.heroman.sample.domain;

import com.heroman.sample.core.exception.InvalidPhoneNumberException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PhoneNumberTest {

    @Test
    void emptyPhoneNumber_shouldHasNullValue() {
        assertThat(PhoneNumber.EMPTY_PHONE_NUMBER.getValue()).isNull();
    }

    @Test
    void create_whenValueIsNull_shouldReturnEmptyPhoneNumber() {
        PhoneNumber phone = PhoneNumber.create(null);
        assertThat(phone).isSameAs(PhoneNumber.EMPTY_PHONE_NUMBER);
    }

    @Test
    void create_whenValueIsEmpty_shouldReturnEmptyPhoneNumber() {
        PhoneNumber phone = PhoneNumber.create("");
        assertThat(phone).isSameAs(PhoneNumber.EMPTY_PHONE_NUMBER);
    }

    @Test
    void create_whenValueIsBlank_shouldReturnEmptyPhoneNumber() {
        PhoneNumber phone = PhoneNumber.create("  ");
        assertThat(phone).isSameAs(PhoneNumber.EMPTY_PHONE_NUMBER);
    }

    @Test
    void create_whenValueIsNotNumber_shouldThrowInvalidPhoneNumberException() {
        assertThrows(InvalidPhoneNumberException.class, () -> PhoneNumber.create("invalid"));
    }

    @Test
    void create_aPlusCharacterCanBeInBeginning() {
        PhoneNumber.create("+123456789");
    }

    @Test
    void create_valueShouldBeSameAsCreateParameter() {
        String value = "02173689846";
        assertThat(PhoneNumber.create(value).getValue()).isEqualTo(value);
    }
}
