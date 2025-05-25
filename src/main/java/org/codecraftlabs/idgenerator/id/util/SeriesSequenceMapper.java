package org.codecraftlabs.idgenerator.id.util;

import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SeriesSequenceMapper {
    private final Map<String, String> sequenceMapper = new ConcurrentHashMap<>();

    public SeriesSequenceMapper() {
        Arrays.stream(SeriesToSequence.values())
                .forEach(item -> sequenceMapper.put(item.getSeriesName(),
                        item.getSequenceName()));
    }

    @Nonnull
    public Optional<String> getSequenceBySeriesName(@Nonnull String seriesName) {
        return Optional.ofNullable(this.sequenceMapper.get(seriesName));
    }
}
