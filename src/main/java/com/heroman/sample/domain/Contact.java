package com.heroman.sample.domain;

public class Contact {
    private String name;
    private PhoneNumber phoneNumber;
    private EmailAddress emailAddress;
    private String organization;
    private String github;

    public String getName() {
        return name;
    }

    public Contact setName(String name) {
        this.name = name;
        return this;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Contact setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public Contact setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getOrganization() {
        return organization;
    }

    public Contact setOrganization(String organization) {
        this.organization = organization;
        return this;
    }

    public String getGithub() {
        return github;
    }

    public Contact setGithub(String github) {
        this.github = github;
        return this;
    }
}
