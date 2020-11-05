package com.heroman.sample.infra.service.contact.query;

import com.heroman.sample.core.Request;
import com.heroman.sample.core.RequestHandler;
import com.heroman.sample.infra.service.contact.dto.SearchContactResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

public class SearchContact extends NamedParameterJdbcDaoSupport
        implements RequestHandler<SearchContact.SearchContactQuery> {

    public SearchContact(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public void execute(SearchContactQuery query) {
        String q = generateQuery(query);
        List<SearchContactResponse.SearchContactDto> result = Objects.requireNonNull(getNamedParameterJdbcTemplate())
                .query(q, new MapSqlParameterSource("name", StringUtils.defaultIfBlank(query.getName(), ""))
                                .addValue("phoneNumber", StringUtils.defaultIfBlank(query.getPhoneNumber(), ""))
                                .addValue("email", StringUtils.defaultIfBlank(query.getEmail(), ""))
                                .addValue("organization", StringUtils.defaultIfBlank(query.getOrganization(), ""))
                                .addValue("github", StringUtils.defaultIfBlank(query.getGithub(), "")),
                        (resultSet, i) -> new SearchContactResponse.SearchContactDto()
                        .setName(resultSet.getString("name"))
                        .setPhoneNumber(resultSet.getString("phoneNumber"))
                        .setEmail(resultSet.getString("emailAddress"))
                        .setOrganization(resultSet.getString("organization"))
                        .setGithub(resultSet.getString("github"))
                );
        query.setResult(result);
    }

    private String generateQuery(SearchContactQuery query) {
        return String.join(" ",
                "select * from contact",
                "where 1=1",
                generateFilter("name", query.getName()),
                generateFilter("phoneNumber", query.getPhoneNumber()),
                generateFilter("email", query.getEmail()),
                generateFilter("organization", query.getOrganization()),
                generateFilter("github", query.getGithub()));
    }

    private String generateFilter(String field, String value) {
        return StringUtils.isNoneBlank(value) ? String.join(" ", "and", field, "=", ":"+field) : "";
    }

    public interface SearchContactQuery extends Request {

        String getName();

        String getPhoneNumber();

        String getEmail();

        String getOrganization();

        String getGithub();

        void setResult(List<SearchContactResponse.SearchContactDto> result);
    }
}
