package org.codecraftlabs.idgenerator.id.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;

@Repository
public class JdbcTemplateDataRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateDataRepository(@Nonnull JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long getNextSequenceValue(@Nonnull String sequenceName) {
        try {
            String statement = String.format("SELECT NEXTVAL('%s')", sequenceName);
            Long id = jdbcTemplate.queryForObject(statement, Long.class);
            if (id == null) {
                throw new DatabaseException("Failed to retrieve next value");
            }
            return id;
        } catch (DataAccessException exception) {
            throw new DatabaseException("Failed to get the next sequence value", exception);
        }
    }
}
