package com.rhyno.misedirty.controller;

import com.rhyno.misedirty.api.AirPollutionApi;
import com.rhyno.misedirty.model.AirPollution;
import com.rhyno.misedirty.repository.AirPollutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public AirPollution getPollution(@RequestParam("station") String station) throws UnsupportedEncodingException {
        final AirPollution pollution = airPollutionApi.getPollution(station);
        return airPollutionRepository.save(pollution);
    }
}
