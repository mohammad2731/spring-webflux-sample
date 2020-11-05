package com.heroman.sample.api;

import com.heroman.sample.core.exception.InvalidNameException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateContactTest {

    private static CreateContact createContact;
    private static CreateContact.CreateContactDa da;

    @BeforeAll
    public static void setup(){
        da = Mockito.mock(CreateContact.CreateContactDa.class);
        createContact = new CreateContact(da);
    }

    @BeforeEach
    void clearState(){
        Mockito.clearInvocations(da);
    }

    @Test
    void whenNameIsBlankShouldThrowInvalidNameException() {
        assertThrows(InvalidNameException.class, () -> new CreateContact(null).execute(new CreateContactCommand()));
    }

    @Test
    void shouldPersistContactWithNameAsGiven() {
        createContact.execute(new CreateContactCommand()
                .withName("test")
        );
        Mockito.verify(da, Mockito.times(1)).persist(Mockito.argThat(c -> "test".equals(c.getName())));
    }

    @Test
    void shouldPersistContactWithPhoneNumberAsGiven() {
        createContact.execute(new CreateContactCommand()
                .withName("test")
                .withPhoneNumber("123456")
        );
        Mockito.verify(da, Mockito.times(1))
                .persist(Mockito.argThat(c -> "123456".equals(c.getPhoneNumber().getValue())));
    }

    @Test
    void whenPhoneNumberIsBlank_shouldPersistContactWithNullPhoneNumber() {
        createContact.execute(new CreateContactCommand()
                .withName("test")
                .withPhoneNumber("")
        );
        Mockito.verify(da, Mockito.times(1))
                .persist(Mockito.argThat(c -> Objects.isNull(c.getPhoneNumber().getValue())));
    }

    @Test
    void shouldPersistContactWithEmailAsGiven() {
        createContact.execute(new CreateContactCommand()
                .withName("test")
                .withEmail("user@test.com")
        );
        Mockito.verify(da, Mockito.times(1))
                .persist(Mockito.argThat(c -> "user@test.com".equals(c.getEmailAddress().getValue())));
    }

    @Test
    void whenEmailIsBlank_shouldPersistContactWithNullEmailAddress() {
        createContact.execute(new CreateContactCommand()
                .withName("test")
                .withEmail("")
        );
        Mockito.verify(da, Mockito.times(1))
                .persist(Mockito.argThat(c -> Objects.isNull(c.getEmailAddress().getValue())));
    }

    @Test
    void shouldPersistContactWithOrganizationAsGiven() {
        createContact.execute(new CreateContactCommand()
                .withName("test")
                .withOrganization("Sun")
        );
        Mockito.verify(da, Mockito.times(1))
                .persist(Mockito.argThat(c -> "Sun".equals(c.getOrganization())));
    }

    @Test
    void shouldPersistContactWithGithubAsGiven() {
        createContact.execute(new CreateContactCommand()
                .withName("test")
                .withGithub("heroman")
        );
        Mockito.verify(da, Mockito.times(1))
                .persist(Mockito.argThat(c -> "heroman".equals(c.getGithub())));
    }

    private static class CreateContactCommand implements CreateContact.CreateContactCommand {
        private String name;
        private String phoneNumber;
        private String email;
        private String organization;
        private String github;

        public CreateContactCommand withName(String name) {
            this.name = name;
            return this;
        }

        public CreateContactCommand withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CreateContactCommand withEmail(String email) {
            this.email = email;
            return this;
        }

        public CreateContactCommand withOrganization(String organization) {
            this.organization = organization;
            return this;
        }

        public CreateContactCommand withGithub(String github) {
            this.github = github;
            return this;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public String getPhoneNumber() {
            return this.phoneNumber;
        }

        @Override
        public String getEmail() {
            return this.email;
        }

        @Override
        public String getOrganization() {
            return this.organization;
        }

        @Override
        public String getGithub() {
            return this.github;
        }

        @Override
        public UUID getUuid() {
            return UUID.randomUUID();
        }
    }
}
