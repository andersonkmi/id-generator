package org.codecraftlabs.idgenerator.id.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SeriesSequenceMapperTest {
    private SeriesSequenceMapper seriesSequenceMapper;

    @BeforeEach
    public void setup() {
        this.seriesSequenceMapper = new SeriesSequenceMapper();
    }

    @Test
    public void validateAllEnumEntriesAreLoaded() {
        var values = SeriesToSequence.values();
        for (SeriesToSequence item : values) {
            assertThat(seriesSequenceMapper.getSequenceBySeriesName(item.getSeriesName())).isPresent();
        }
    }

    @Test
    public void when_series_name_is_not_present_should_return_empty() {
        var result = this.seriesSequenceMapper.getSequenceBySeriesName("fake");
        assertThat(result).isEmpty();
    }

    @Test
    public void when_series_name_is_present_should_return_value() {
        var result = this.seriesSequenceMapper.getSequenceBySeriesName("default");
        assertThat(result).isPresent();
    }
}
