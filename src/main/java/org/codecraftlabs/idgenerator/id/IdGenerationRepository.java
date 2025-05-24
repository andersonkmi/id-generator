package org.codecraftlabs.idgenerator.id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

    private void initializeSequenceMapping() {
        idSequences.put("default", "default_sequence");
    }
}
