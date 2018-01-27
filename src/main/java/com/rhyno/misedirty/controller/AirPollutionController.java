package com.rhyno.misedirty.controller;

import com.rhyno.misedirty.apis.AirPollutionApi;
    import com.rhyno.misedirty.model.AirPollution;
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

    @Autowired
    public AirPollutionController(AirPollutionApi airPollutionApi) {
        this.airPollutionApi = airPollutionApi;
    }

    @GetMapping
    public AirPollution getPollution(@RequestParam("station") String station) throws UnsupportedEncodingException {
        return airPollutionApi.getPollution(station);
    }
}
