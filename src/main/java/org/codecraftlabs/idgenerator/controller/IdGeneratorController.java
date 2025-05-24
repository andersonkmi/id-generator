package org.codecraftlabs.idgenerator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class IdGeneratorController extends BaseControllerV1 {
    private static final Logger logger = LoggerFactory.getLogger(IdGeneratorController.class);


    @GetMapping(value = "/id/{seriesName}",
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<IdResponse> getPaginatedClients(@PathVariable String seriesName, @RequestParam(value = "encoding", required = false) String encoding) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
