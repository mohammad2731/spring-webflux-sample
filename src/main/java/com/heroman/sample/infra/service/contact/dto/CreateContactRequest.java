package com.heroman.sample.infra.service.contact.dto;

import com.heroman.sample.api.CreateContact;

public class CreateContactRequest implements CreateContact.CreateContactCommand {
    private String name;
    private String phoneNumber;
    private String email;
    private String organization;
    private String github;

    public CreateContactRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public CreateContactRequest setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CreateContactRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CreateContactRequest setOrganization(String organization) {
        this.organization = organization;
        return this;
    }

    public String getOrganization() {
        return organization;
    }

    public CreateContactRequest setGithub(String github) {
        this.github = github;
        return this;
    }

    public String getGithub() {
        return github;
    }
}
