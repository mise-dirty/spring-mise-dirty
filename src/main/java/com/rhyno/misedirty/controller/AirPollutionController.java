package com.rhyno.misedirty.controller;

import com.rhyno.misedirty.api.AirPollutionApi;
import com.rhyno.misedirty.model.AirPollution;
import com.rhyno.misedirty.repository.AirPollutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(value = "/api/v1/pollution", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AirPollutionController {
    private final AirPollutionApi airPollutionApi;
    private final AirPollutionRepository airPollutionRepository;

    @Autowired
    public AirPollutionController(AirPollutionApi airPollutionApi, AirPollutionRepository airPollutionRepository) {
        this.airPollutionApi = airPollutionApi;
        this.airPollutionRepository = airPollutionRepository;
    }

    @GetMapping
    public Mono<AirPollution> getPollution(@RequestParam("station") String station) throws UnsupportedEncodingException {
        final AirPollution pollution = airPollutionApi.getPollution(station);
        return airPollutionRepository.save(pollution);
    }

    @GetMapping("{id}")
    public Mono<AirPollution> getPollutionById(@PathVariable(name = "id") String id) {
        return airPollutionRepository.findById(id);
    }
}
