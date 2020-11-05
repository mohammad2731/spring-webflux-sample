package com.heroman.sample.infra.service.contact.da;

import com.heroman.sample.api.CreateContact;
import com.heroman.sample.domain.Contact;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CreateContactDa extends NamedParameterJdbcDaoSupport implements CreateContact.CreateContactDa {

    public CreateContactDa(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public Contact persist(Contact contact) {
        String q = "insert into contact (name, phoneNumber, emailAddress, organization, github) values" +
                "(:name, :phoneNumber, :emailAddress, :organization, :github)";
        getNamedParameterJdbcTemplate()
                .update(q, new MapSqlParameterSource("name", contact.getName())
                        .addValue("phoneNumber", contact.getPhoneNumber().getValue())
                        .addValue("emailAddress", contact.getEmailAddress().getValue())
                        .addValue("organization", contact.getOrganization())
                        .addValue("github", contact.getGithub())
                );
        return contact;
    }
}
