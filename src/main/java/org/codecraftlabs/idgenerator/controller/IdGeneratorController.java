package org.codecraftlabs.idgenerator.controller;

import org.codecraftlabs.idgenerator.id.IdGenerationProcessor;
import org.codecraftlabs.idgenerator.id.IdGenerationServiceFactory;
import org.codecraftlabs.idgenerator.id.IdNotGeneratedException;
import org.codecraftlabs.idgenerator.id.InvalidSeriesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
            String type = format != null ? format : "default";
            Optional<IdGenerationProcessor> processor = idGenerationServiceFactory.getProcessor(type);
            if (processor.isEmpty()) {
                throw new ResponseStatusException(BAD_REQUEST, "Invalid format requested");
            }

            String id = processor.get().generateId(seriesName);
            logger.info("Generated id '{}' for series '{}' using '{}' format", id, seriesName, type);

            IdResponse response = new IdResponse(id, seriesName);
            return status(OK).body(response);
        } catch (IdNotGeneratedException exception) {
            logger.error("Id not generated", exception);
            throw new ResponseStatusException(BAD_REQUEST, "Id not generated", exception);
        } catch (InvalidSeriesException exception) {
            logger.error("Series is invalid", exception);
            throw new ResponseStatusException(NOT_FOUND, "Series is invalid", exception);
        }
    }
}
