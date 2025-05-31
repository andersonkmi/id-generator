package org.codecraftlabs.idgenerator.id.repository;

import org.codecraftlabs.idgenerator.id.util.SeriesSequenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.Optional;

@Component
public class IdGenerationRepository {
    private final JdbcTemplateDataRepository jdbcTemplateDataRepository;
    private final SeriesSequenceMapper seriesSequenceMapper;

    @Autowired
    public IdGenerationRepository(@Nonnull JdbcTemplateDataRepository jdbcTemplateDataRepository,
                                  @Nonnull SeriesSequenceMapper seriesSequenceMapper) {
        this.jdbcTemplateDataRepository = jdbcTemplateDataRepository;
        this.seriesSequenceMapper = seriesSequenceMapper;
    }

    public long getId(@Nonnull String seriesName) {
        String sequenceName = getSequenceName(seriesName);
        return jdbcTemplateDataRepository.getNextSequenceValue(sequenceName);
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
