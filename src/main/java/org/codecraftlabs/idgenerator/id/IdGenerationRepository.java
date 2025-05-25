package org.codecraftlabs.idgenerator.id;

import org.codecraftlabs.idgenerator.id.util.SeriesSequenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.Optional;

@Repository
public class IdGenerationRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SeriesSequenceMapper seriesSequenceMapper;

    @Autowired
    public IdGenerationRepository(@Nonnull JdbcTemplate jdbcTemplate,
                                  @Nonnull SeriesSequenceMapper seriesSequenceMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.seriesSequenceMapper = seriesSequenceMapper;
    }

    @Transactional(rollbackFor = DatabaseException.class)
    @Nonnull
    public String getId(@Nonnull String seriesName) {
        try {
            String sequenceName = getSequenceName(seriesName);
            String statement = String.format("SELECT NEXTVAL('%s')", sequenceName);
            Long id = jdbcTemplate.queryForObject(statement, Long.class);
            if (id == null) {
                throw new DatabaseException("Failed to retrieve next value");
            }
            return String.valueOf(id);
        } catch (DataAccessException exception) {
            throw new DatabaseException("Failed to get the next sequence value",
                    exception);
        }
    }

    @Nonnull
    private String getSequenceName(@Nonnull String type) {
        Optional<String> sequenceName = seriesSequenceMapper.getSequenceBySeriesName(type);
        if (sequenceName.isEmpty()) {
            throw new SequenceNotFoundException("Invalid sequence name");
        }
        return sequenceName.get();
    }
}
