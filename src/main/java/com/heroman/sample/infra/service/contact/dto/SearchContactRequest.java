package com.heroman.sample.infra.service.contact.dto;

import com.heroman.sample.infra.service.contact.query.SearchContact;

import java.util.List;

public class SearchContactRequest implements SearchContact.SearchContactQuery {

    private String name;
    private String phoneNumber;
    private String email;
    private String organization;
    private String github;
    private SearchContactResponse response=new SearchContactResponse();

    @Override
    public String getName() {
        return name;
    }

    public SearchContactRequest setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SearchContactRequest setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public SearchContactRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String getOrganization() {
        return organization;
    }

    public SearchContactRequest setOrganization(String organization) {
        this.organization = organization;
        return this;
    }

    @Override
    public String getGithub() {
        return github;
    }

    @Override
    public void setResult(List<SearchContactResponse.SearchContactDto> result) {
        this.response.setData(result);
    }

    public SearchContactRequest setGithub(String github) {
        this.github = github;
        return this;
    }

    public SearchContactResponse getResponse() {
        return response;
    }

    public void setResponse(SearchContactResponse response) {
        this.response = response;
    }
}
