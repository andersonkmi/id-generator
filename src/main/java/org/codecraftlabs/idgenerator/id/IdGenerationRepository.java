package org.codecraftlabs.idgenerator.id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

@Repository
public class IdGenerationRepository {
    private final Map<String, String> idSequences = new HashMap<>();

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IdGenerationRepository(@Nonnull JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        initializeSequenceMapping();
    }

    @Transactional(rollbackFor = DatabaseException.class)
    @Nonnull
    public String getId(@Nonnull String seriesName) {
        try {
            String sequenceName = getSequenceName(seriesName);
            String statement = String.format("select next_val('%s')", sequenceName);
            Long id = jdbcTemplate.queryForObject(statement, Long.class);
            if (id == null) {
                throw new DatabaseException("Failed to retrieve next value");
            }

            return String.valueOf(id);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Client could not be inserted into the database",
                    exception);
        }
    }

    private void initializeSequenceMapping() {
        idSequences.put("default", "default_sequence");
    }

    @Nonnull
    private String getSequenceName(@Nonnull String type) {
        String sequenceName = idSequences.getOrDefault(type, "");
        if (sequenceName.isBlank()) {
            throw new DatabaseException("Invalid sequence name");
        }
        return sequenceName;
    }
}
