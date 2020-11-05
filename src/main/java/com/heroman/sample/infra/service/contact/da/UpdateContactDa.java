package com.heroman.sample.infra.service.contact.da;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.UUID;

@Repository
public class UpdateContactDa extends NamedParameterJdbcDaoSupport {
    public UpdateContactDa(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public int update(UUID uuid, String repos) {
        String q = "update contact set repositories = :repos where uuid = :uuid";
        return getNamedParameterJdbcTemplate().update(q, new MapSqlParameterSource("uuid", uuid.toString())
                .addValue("repos", repos));
    }
}
