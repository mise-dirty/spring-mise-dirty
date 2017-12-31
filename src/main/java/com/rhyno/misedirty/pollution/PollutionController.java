package com.rhyno.misedirty.pollution;

import com.rhyno.misedirty.apis.PmApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/api/v1/pollution")
public class PollutionController {
    private PmApi pmApi;

    @Autowired
    public PollutionController(PmApi pmApi) {
        this.pmApi = pmApi;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Pollution> getPollution(@RequestParam("station") String station,
                                        @RequestParam("period") String period) {
        return pmApi.getPollution(station, period);
    }
}
