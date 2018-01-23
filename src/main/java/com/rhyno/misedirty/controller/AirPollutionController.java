package com.rhyno.misedirty.controller;

import com.rhyno.misedirty.apis.model.AirPollution;
import com.rhyno.misedirty.apis.AirPollutionApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api/v1/pollution")
public class AirPollutionController {
    private AirPollutionApi airPollutionApi;

    @Autowired
    public AirPollutionController(AirPollutionApi airPollutionApi) {
        this.airPollutionApi = airPollutionApi;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(consumes = "application/json", produces = "application/json")
    public AirPollution getPollution(@RequestParam("station") String station) {
        return airPollutionApi.getPollution(station);
    }
}
