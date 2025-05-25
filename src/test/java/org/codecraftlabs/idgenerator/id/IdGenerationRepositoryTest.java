package org.codecraftlabs.idgenerator.id;

import org.codecraftlabs.idgenerator.id.util.JdbcTemplateDataRepository;
import org.codecraftlabs.idgenerator.id.util.SeriesSequenceMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class IdGenerationRepositoryTest {
    @Mock
    private JdbcTemplateDataRepository jdbcTemplateDataRepository;

    @Mock
    private SeriesSequenceMapper seriesSequenceMapper;

    @InjectMocks
    private IdGenerationRepository idGenerationRepository;

    @Test
    public void when_series_is_not_present_should_raise_exception() {
        // Setup mocks
        Mockito.when(seriesSequenceMapper.getSequenceBySeriesName(anyString())).thenReturn(Optional.empty());

        assertThatExceptionOfType(SequenceNotFoundException.class)
                .isThrownBy(() -> this.idGenerationRepository.getId("default"));
    }

    @Test
    public void when_jdbc_raises_exception_should_raise_exception() {
        // Setup mocks
        Mockito.when(seriesSequenceMapper.getSequenceBySeriesName(anyString())).thenReturn(Optional.of("default"));
        Mockito.when(jdbcTemplateDataRepository.getNextSequenceValue(anyString())).thenThrow(DatabaseException.class);

        assertThatExceptionOfType(DatabaseException.class)
                .isThrownBy(() -> this.idGenerationRepository.getId("default"));
    }
}