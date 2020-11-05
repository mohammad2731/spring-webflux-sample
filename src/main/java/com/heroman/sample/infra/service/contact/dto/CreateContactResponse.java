package com.heroman.sample.infra.service.contact.dto;

public class CreateContactResponse {
    private String name;
    private String phoneNumber;
    private String email;
    private String organization;
    private String github;

    public CreateContactResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public CreateContactResponse setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CreateContactResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CreateContactResponse setOrganization(String organization) {
        this.organization = organization;
        return this;
    }

    public String getOrganization() {
        return organization;
    }

    public CreateContactResponse setGithub(String github) {
        this.github = github;
        return this;
    }

    public String getGithub() {
        return github;
    }
}
