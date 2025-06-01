package org.codecraftlabs.idgenerator.controller;

import org.codecraftlabs.idgenerator.id.processor.IdGenerationProcessor;
import org.codecraftlabs.idgenerator.id.processor.IdGenerationServiceFactory;
import org.codecraftlabs.idgenerator.id.processor.IdNotGeneratedException;
import org.codecraftlabs.idgenerator.id.processor.InvalidSeriesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@RestController
public class IdGeneratorController extends BaseControllerV1 {
    private static final Logger logger = LoggerFactory.getLogger(IdGeneratorController.class);

    private final IdGenerationServiceFactory idGenerationServiceFactory;

    @Autowired
    public IdGeneratorController(@Nonnull IdGenerationServiceFactory idGenerationServiceFactory) {
        this.idGenerationServiceFactory = idGenerationServiceFactory;
    }

    @GetMapping(value = "/id/{seriesName}",
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<IdResponse> getPaginatedClients(@PathVariable String seriesName,
                                                          @RequestParam(value = "format", required = false) String format) {
        try {
            String type = getIdGeneratorProcessorType(format);
            IdGenerationProcessor processor = getProcessor(type);
            String id = processor.generateId(seriesName);
            logger.info("Generated id '{}' for series '{}' using '{}' format", id, seriesName, type);

            return generateResponse(id, seriesName);
        } catch (IdNotGeneratedException exception) {
            logger.error("Id not generated", exception);
            throw new ResponseStatusException(BAD_REQUEST, "Id not generated", exception);
        } catch (InvalidSeriesException exception) {
            logger.error("Series is invalid", exception);
            throw new ResponseStatusException(NOT_FOUND, "Series is invalid", exception);
        }
    }

    @Nonnull
    private String getIdGeneratorProcessorType(@CheckForNull String format) {
        return format != null && !format.isBlank() ? format : "default";
    }

    @Nonnull
    private IdGenerationProcessor getProcessor(@Nonnull String type) {
        Optional<IdGenerationProcessor> processor = idGenerationServiceFactory.getProcessor(type);
        if (processor.isEmpty()) {
            throw new ResponseStatusException(BAD_REQUEST, "Invalid format requested");
        }
        return processor.get();
    }

    @Nonnull
    private ResponseEntity<IdResponse> generateResponse(@Nonnull String id, @Nonnull String seriesName) {
        return status(OK).body(new IdResponse(id, seriesName));
    }
}
