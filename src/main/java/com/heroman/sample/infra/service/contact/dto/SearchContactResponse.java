package com.heroman.sample.infra.service.contact.dto;

import java.util.List;

public class SearchContactResponse {
    private List<SearchContactDto> date;

    public void setData(List<SearchContactDto> data) {
        this.date = data;
    }

    public List<SearchContactDto> getDate() {
        return date;
    }

    public static class SearchContactDto {
        private String name;
        private String phoneNumber;
        private String emailAddress;
        private String organization;
        private String github;

        public String getName() {
            return name;
        }

        public SearchContactDto setName(String name) {
            this.name = name;
            return this;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public SearchContactDto setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public SearchContactDto setEmail(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public String getOrganization() {
            return organization;
        }

        public SearchContactDto setOrganization(String organization) {
            this.organization = organization;
            return this;
        }

        public String getGithub() {
            return github;
        }

        public SearchContactDto setGithub(String github) {
            this.github = github;
            return this;
        }
    }
}
